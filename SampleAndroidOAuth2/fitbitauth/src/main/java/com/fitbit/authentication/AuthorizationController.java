package com.fitbit.authentication;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jboggess on 9/14/16.
 */
public class AuthorizationController implements UrlChangeHandler {
    private final static String TAG = "AuthorizationController";
    private static final String AUTHORIZE_URL_FORMAT = "https://www.fitbit.com/oauth2/authorize?response_type=token&client_id=%s&redirect_uri=%s&scope=%s&expires_in=%d";
    private static final Pattern TOKEN_MATCH_PATTERN = Pattern.compile("#access_token=(.+)&");
    private static final Pattern DISMISSED_PATTERN = Pattern.compile("error_description=The\\+user\\+denied\\+the\\+request");
    private WebView webView;
    private ClientCredentials clientCredentials;
    private AuthenticationHandler authenticationHandler;

    public AuthorizationController(WebView webView, ClientCredentials clientCredentials, AuthenticationHandler authenticationHandler) {
        this.webView = webView;
        this.clientCredentials = clientCredentials;
        this.authenticationHandler = authenticationHandler;
    }

    public void authenticate(@NonNull Long expiresIn, Set<Scope> scopes) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new AuthenticationWebviewClient(this));
        String url = String.format(
                Locale.ENGLISH,
                AUTHORIZE_URL_FORMAT,
                clientCredentials.getClientId(),
                clientCredentials.getRedirectUrl(),
                TextUtils.join("%20", scopes),
                expiresIn);
        webView.loadUrl(url);
    }

    private List<Scope> parseScopes(String scopes) {
        String[] scopesArray = scopes.split(" ");
        List<Scope> scopesList = new ArrayList<>();
        for (String scopeStr : scopesArray) {
            Scope scope = Scope.fromString(scopeStr);
            if (scope != null) {
                scopesList.add(scope);
            }
        }

        return scopesList;
    }


    private void parseSuccess(String urlString) {
        urlString = urlString.replaceFirst("#", "?");
        Uri uri = Uri.parse(urlString);

        //Save Auth Token
        final String accessToken = uri.getQueryParameter("access_token");
        Long expiresOn = Long.parseLong(uri.getQueryParameter("expires_in")) + System.currentTimeMillis() / 1000;
        List<Scope> scopes = parseScopes(uri.getQueryParameter("scope"));
        final AccessToken accessTokenObject = new AccessToken(accessToken, expiresOn, scopes);

        authenticationHandler.onAuthFinished(AuthenticationResult.success(accessTokenObject));
    }


    @Override
    public void onUrlChanged(String newUrl) {
        if (newUrl.startsWith(clientCredentials.getRedirectUrl())) {
            webView.setVisibility(View.GONE);
            Matcher successMatcher = TOKEN_MATCH_PATTERN.matcher(newUrl);
            Matcher dismissedMatcher = DISMISSED_PATTERN.matcher(newUrl);
            if (successMatcher.find()) {
                parseSuccess(newUrl);
            } else if (dismissedMatcher.find()) {
                authenticationHandler.onAuthFinished(AuthenticationResult.dismissed());
            } else {
                Log.e(TAG, "Error getting access code from url");
                authenticationHandler.onAuthFinished(AuthenticationResult.error(
                        String.format("Url missing access code: %s", newUrl)));
            }
        }
    }

    @Override
    public void onLoadError(int errorCode, CharSequence description) {
        authenticationHandler.onAuthFinished(AuthenticationResult.error(description.toString()));
    }
}
