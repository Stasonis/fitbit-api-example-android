package com.fitbit.api;

import com.fitbit.api.exceptions.MissingScopesException;
import com.fitbit.api.exceptions.TokenExpiredException;
import com.fitbit.authentication.AccessToken;
import com.fitbit.authentication.AuthenticationManager;
import com.fitbit.authentication.Scope;

import android.app.Activity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jboggess on 9/19/16.
 */
public class APIUtils {

    public static void validateToken(Activity contextActivity, AccessToken accessToken, Scope... scopes) throws MissingScopesException, TokenExpiredException {
        Set<Scope> requiredScopes = new HashSet<>(Arrays.asList(scopes));

        requiredScopes.removeAll(accessToken.getScopes());

        if (!requiredScopes.isEmpty()) {
            throw new MissingScopesException(requiredScopes);
        }

        if (accessToken.hasExpired()) {
            // Check to see if we should logout
            if (AuthenticationManager.getAuthenticationConfiguration().isLogoutOnAuthFailure()) {
                AuthenticationManager.logout(contextActivity);
            } else {
                throw new TokenExpiredException();
            }
        }
    }
}
