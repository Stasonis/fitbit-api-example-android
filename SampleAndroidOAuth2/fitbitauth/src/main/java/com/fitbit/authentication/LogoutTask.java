package com.fitbit.authentication;

import com.fitbit.fitbitcommon.network.BasicHttpRequest;
import com.fitbit.fitbitcommon.network.BasicHttpRequestBuilder;
import com.fitbit.fitbitcommon.network.BasicHttpResponse;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Base64;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by jboggess on 9/19/16.
 */
public class LogoutTask extends AsyncTask<Handler, String, Void> {

    private static final String REVOKE_URL = "https://api.fitbit.com/oauth2/revoke";
    private final ClientCredentials clientCredentials;
    private final AccessToken accessToken;
    private final LogoutTaskCompletionHandler logoutTaskCompletionHandler;

    public LogoutTask(ClientCredentials clientCredentials, AccessToken accessToken, @NonNull LogoutTaskCompletionHandler logoutTaskCompletionHandler) {
        this.clientCredentials = clientCredentials;
        this.accessToken = accessToken;
        this.logoutTaskCompletionHandler = logoutTaskCompletionHandler;
    }

    @Override
    protected Void doInBackground(Handler... handlers) {
        String tokenString = String.format("%s:%s", clientCredentials.getClientId(), clientCredentials.getClientSecret());
        String token = Base64.encodeToString(tokenString.getBytes(Charset.forName("UTF-8")), 0);

        BasicHttpRequest request = BasicHttpRequestBuilder.create()
                .setUrl(REVOKE_URL)
                .setContentType("application/json")
                .setAuthorization(String.format("Basic %s", token).trim())
                .setMethod("POST")
                .addQueryParam("token", accessToken.getAccessToken())
                .build();

        try {
            final BasicHttpResponse response = request.execute();
            final String responseBodyStr = response.getBodyAsString();

            handlers[0].post(new Runnable() {
                @Override
                public void run() {
                    if (response.isSuccessful()) {
                        logoutTaskCompletionHandler.logoutSuccess();
                    } else {
                        logoutTaskCompletionHandler.logoutError(responseBodyStr);
                    }
                }
            });

        } catch (final IOException e) {
            handlers[0].post(new Runnable() {
                @Override
                public void run() {
                    logoutTaskCompletionHandler.logoutError(e.getMessage());
                }
            });
        }
        return null;
    }
}
