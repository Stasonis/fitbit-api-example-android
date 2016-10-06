package com.fitbit.api;

import com.fitbit.authentication.AuthenticationManager;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jboggess on 9/19/16.
 */
public class ResourceLoader<T> {

    private String urlFormat;
    private Class<T> classType;

    public ResourceLoader(String urlFormat, Class<T> classType) {
        this.urlFormat = urlFormat;
        this.classType = classType;
    }

    public void loadResource(Activity contextActivity, @NonNull ResourceLoadedHandler<T> resourceLoadedHandler, String... pathParams) {

        String url = urlFormat;
        if (pathParams != null && pathParams.length > 0) {
            url = String.format(urlFormat, pathParams);
        }

        new LoadResourceTask(url, new Handler(), classType, resourceLoadedHandler).execute(contextActivity);
    }


    private class LoadResourceTask extends AsyncTask<Activity, Void, Void> {

        private final String url;
        private final Class<T> classType;
        private final ResourceLoadedHandler<T> resourceLoadedHandler;
        private final Handler handler;

        public LoadResourceTask(String url, Handler handler, Class<T> classType, @NonNull ResourceLoadedHandler<T> resourceLoadedHandler) {
            this.url = url;
            this.classType = classType;
            this.handler = handler;
            this.resourceLoadedHandler = resourceLoadedHandler;
        }

        @Override
        protected Void doInBackground(final Activity... contextActivity) {

            try {
                OkHttpClient client = new OkHttpClient();

                Request request = AuthenticationManager
                        .createSignedRequest()
                        .header("Content-Type", "Application/json")
                        .url(url)
                        .build();

                final Response response = client.newCall(request).execute();
                int responseCode = response.code();
                final String json = response.body().string();
                if (response.isSuccessful()) {
                    final T resource = new Gson().fromJson(json, classType);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            resourceLoadedHandler.onResourceLoaded(resource);
                        }
                    });
                } else {
                    if (responseCode == 401 && AuthenticationManager.getAuthenticationConfiguration().isLogoutOnAuthFailure()) {
                        // Token may have been revoked or expired
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                AuthenticationManager.logout(contextActivity[0]);
                            }
                        });
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                resourceLoadedHandler.onResourceLoadError(json);
                            }
                        });
                    }
                }
            } catch (final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        resourceLoadedHandler.onResourceLoadError(e.getMessage());
                    }
                });
            }

            return null;
        }
    }
}
