package com.fitbit.authentication;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by jboggess on 9/14/16.
 */
public class AuthenticationWebviewClient extends WebViewClient {

    private UrlChangeHandler urlChangeHandler;

    public AuthenticationWebviewClient(UrlChangeHandler urlChangeHandler) {
        this.urlChangeHandler = urlChangeHandler;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return loadUrl(view, url);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return loadUrl(view, request.getUrl().toString());
    }

    private boolean loadUrl(WebView view, String url) {
        view.loadUrl(url);
        urlChangeHandler.onUrlChanged(url);
        return false;
    }


    @SuppressWarnings("deprecation")
    @Override
    public void onReceivedError(WebView view, final int errorCode,
                                final String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        urlChangeHandler.onLoadError(errorCode, description);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        urlChangeHandler.onLoadError(error.getErrorCode(), error.getDescription());
    }
}