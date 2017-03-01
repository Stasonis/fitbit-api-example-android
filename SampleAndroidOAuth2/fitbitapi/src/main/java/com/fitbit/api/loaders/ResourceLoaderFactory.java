package com.fitbit.api.loaders;

import com.fitbit.authentication.Scope;

import android.app.Activity;
import android.os.Handler;

/**
 * Created by jboggess on 9/19/16.
 */
public class ResourceLoaderFactory<T> {

    private String urlFormat;
    private Class<T> classType;

    public ResourceLoaderFactory(String urlFormat, Class<T> classType) {
        this.urlFormat = urlFormat;
        this.classType = classType;
    }

    public ResourceLoader<T> newResourceLoader(Activity contextActivity, Scope[] requiredScopes, String... pathParams) {

        String url = urlFormat;
        if (pathParams != null && pathParams.length > 0) {
            url = String.format(urlFormat, pathParams);
        }

        return new ResourceLoader<T>(contextActivity, url, requiredScopes, new Handler(), classType);
    }
}
