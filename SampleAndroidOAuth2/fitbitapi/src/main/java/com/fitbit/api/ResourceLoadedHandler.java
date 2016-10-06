package com.fitbit.api;

/**
 * Created by jboggess on 9/19/16.
 */
public interface ResourceLoadedHandler<T> {

    void onResourceLoaded(T resource);

    void onResourceLoadError(String errorMessage);
}
