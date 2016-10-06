package com.fitbit.api.services;

import com.fitbit.api.APIUtils;
import com.fitbit.api.MissingScopesException;
import com.fitbit.api.ResourceLoadedHandler;
import com.fitbit.api.ResourceLoader;
import com.fitbit.api.TokenExpiredException;
import com.fitbit.api.models.User;
import com.fitbit.api.models.UserContainer;
import com.fitbit.authentication.AuthenticationManager;
import com.fitbit.authentication.Scope;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * Created by jboggess on 9/14/16.
 */
public class UserService {

    private final static String USER_URL = "https://api.fitbit.com/1/user/-/profile.json";
    private static final ResourceLoader<UserContainer> USER_PROFILE_LOADER = new ResourceLoader<>(USER_URL, UserContainer.class);

    public interface UserHandler {
        void onUserLoaded(User user);

        void onErrorLoadingUser(String errorMessage);
    }

    public static void getLoggedInUserProfile(Activity activityContext, @NonNull final UserHandler userHandler) throws MissingScopesException, TokenExpiredException {
        APIUtils.validateToken(activityContext, AuthenticationManager.getCurrentAccessToken(), Scope.profile);
        USER_PROFILE_LOADER.loadResource(activityContext, new ResourceLoadedHandler<UserContainer>() {
            @Override
            public void onResourceLoaded(UserContainer resource) {
                userHandler.onUserLoaded(resource.getUser());
            }

            @Override
            public void onResourceLoadError(String errorMessage) {
                userHandler.onErrorLoadingUser(errorMessage);
            }
        });
    }

}
