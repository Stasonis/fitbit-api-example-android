package com.fitbit.authentication;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Base64;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("token", accessToken.getAccessToken())
                .build();

        Request request = new Request.Builder()
                .url(REVOKE_URL)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", String.format("Basic %s", token).trim())
                .method("POST", body)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final String responseBodyStr = response.body().string();

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
