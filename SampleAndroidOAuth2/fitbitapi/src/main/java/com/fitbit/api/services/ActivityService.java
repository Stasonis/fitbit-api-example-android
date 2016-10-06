package com.fitbit.api.services;

import com.fitbit.api.APIUtils;
import com.fitbit.api.MissingScopesException;
import com.fitbit.api.ResourceLoadedHandler;
import com.fitbit.api.ResourceLoader;
import com.fitbit.api.TokenExpiredException;
import com.fitbit.api.models.Activities;
import com.fitbit.authentication.AuthenticationManager;
import com.fitbit.authentication.Scope;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * Created by jboggess on 10/3/16.
 */
public class ActivityService {

    private final static String ACTIVITIES_URL = "https://api.fitbit.com/1/user/-/activities.json";
    private static final ResourceLoader<Activities> USER_ACTIVITIES_LOADER = new ResourceLoader<>(ACTIVITIES_URL, Activities.class);

    public static void getUserActivities(Activity activityContext, @NonNull final ResourceLoadedHandler<Activities> handler) throws MissingScopesException, TokenExpiredException {
        APIUtils.validateToken(activityContext, AuthenticationManager.getCurrentAccessToken(), Scope.activity);
        USER_ACTIVITIES_LOADER.loadResource(activityContext, handler);
    }

}
