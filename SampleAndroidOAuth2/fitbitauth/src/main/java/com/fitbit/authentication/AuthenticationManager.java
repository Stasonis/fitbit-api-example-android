package com.fitbit.authentication;

import com.fitbit.authentication.ui.LoginActivity;
import com.fitbit.fitbitcommon.network.BasicHttpRequestBuilder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.android.annotations.Nullable;
import com.sveinungkb.SecurePreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jboggess on 9/14/16.
 */
public class AuthenticationManager {

    private static final int RESULT_CODE = 1;
    private static final String AUTH_TOKEN_KEY = "AUTH_TOKEN";
    private static boolean configured = false;
    private static AuthenticationConfiguration authenticationConfiguration;
    private static AccessToken currentAccessToken;
    private static SecurePreferences preferences;

    public static void configure(Context context, AuthenticationConfiguration authenticationConfiguration) {
        AuthenticationManager.authenticationConfiguration = authenticationConfiguration;
        preferences = new SecurePreferences(context, "FITBIT_AUTHENTICATION_PREFERENCES", authenticationConfiguration.getEncryptionKey(), true);
        configured = true;
    }

    public static synchronized AccessToken getCurrentAccessToken() {
        checkPreconditions();
        if (currentAccessToken == null) {
            currentAccessToken = AccessToken.fromBase64(preferences.getString(AUTH_TOKEN_KEY));
        }
        return currentAccessToken;
    }

    public static synchronized void setCurrentAccessToken(AccessToken currentAccessToken) {
        checkPreconditions();
        AuthenticationManager.currentAccessToken = currentAccessToken;

        //Save to shared preferences
        preferences.put(AUTH_TOKEN_KEY, currentAccessToken == null ? null : currentAccessToken.toBase64String());
    }

    public static void login(Activity activity) {
        Set<Scope> scopes = new HashSet<>();
        scopes.addAll(authenticationConfiguration.getRequiredScopes());
        scopes.addAll(authenticationConfiguration.getOptionalScopes());

        Intent intent = LoginActivity.createIntent(
                activity,
                authenticationConfiguration.getClientCredentials(),
                authenticationConfiguration.getTokenExpiresIn(),
                scopes);

        activity.startActivityForResult(intent, RESULT_CODE);
    }

    public static boolean onActivityResult(int requestCode, int resultCode, Intent data, @NonNull AuthenticationHandler authenticationHandler) {
        checkPreconditions();
        switch (requestCode) {
            case (RESULT_CODE): {
                if (resultCode == Activity.RESULT_OK) {
                    AuthenticationResult authenticationResult = data.getParcelableExtra(LoginActivity.AUTHENTICATION_RESULT_KEY);

                    if (authenticationResult.isSuccessful()) {
                        Set<Scope> grantedScopes = new HashSet<>(authenticationResult.getAccessToken().getScopes());
                        Set<Scope> requiredScopes = new HashSet<>(authenticationConfiguration.getRequiredScopes());

                        requiredScopes.removeAll(grantedScopes);
                        if (requiredScopes.size() > 0) {
                            authenticationResult = AuthenticationResult.missingRequiredScopes(requiredScopes);
                        } else {
                            setCurrentAccessToken(authenticationResult.getAccessToken());
                        }
                    }

                    authenticationHandler.onAuthFinished(authenticationResult);
                } else {
                    authenticationHandler.onAuthFinished(AuthenticationResult.dismissed());
                }
                return true;
            }
        }

        return false;
    }

    public static boolean isLoggedIn() {
        checkPreconditions();
        AccessToken currentAccessToken = getCurrentAccessToken();
        return currentAccessToken != null && !currentAccessToken.hasExpired();
    }

    public static void logout(final Activity contextActivity) {
        logout(contextActivity, null);
    }

    public static void logout(final Activity contextActivity, @Nullable final LogoutTaskCompletionHandler logoutTaskCompletionHandler) {
        checkPreconditions();
        if (!isLoggedIn()) {
            return;
        }

        Handler handler = new Handler();
        new LogoutTask(getAuthenticationConfiguration().getClientCredentials(), getCurrentAccessToken(),
                new LogoutTaskCompletionHandler() {
                    @Override
                    public void logoutSuccess() {
                        Intent beforeLoginActivity = authenticationConfiguration.getBeforeLoginActivity();
                        if (beforeLoginActivity != null) {
                            contextActivity.startActivity(beforeLoginActivity);
                        }
                        if (logoutTaskCompletionHandler != null) {
                            logoutTaskCompletionHandler.logoutSuccess();
                        }
                    }

                    @Override
                    public void logoutError(String message) {
                        Intent beforeLoginActivity = authenticationConfiguration.getBeforeLoginActivity();
                        if (beforeLoginActivity != null) {
                            contextActivity.startActivity(beforeLoginActivity);
                        }
                        if (logoutTaskCompletionHandler != null) {
                            logoutTaskCompletionHandler.logoutError(message);
                        }
                    }
                })
                .execute(handler);

        setCurrentAccessToken(null);
    }

    public static AuthenticationConfiguration getAuthenticationConfiguration() {
        checkPreconditions();
        return authenticationConfiguration;
    }

    public static BasicHttpRequestBuilder createSignedRequest() {
        BasicHttpRequestBuilder basicHttpRequestBuilder = BasicHttpRequestBuilder.create();
        authenticationConfiguration.getRequestSigner().signRequest(basicHttpRequestBuilder);
        return basicHttpRequestBuilder;
    }


    private static void checkPreconditions() {
        if (!configured) {
            throw new IllegalArgumentException("You must call `configure` on AuthenticationManager before using its methods!");
        }
    }

}
