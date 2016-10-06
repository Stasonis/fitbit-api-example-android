package com.fitbit.sampleandroidoauth2;

import com.fitbit.authentication.AuthenticationConfiguration;
import com.fitbit.authentication.AuthenticationConfigurationBuilder;
import com.fitbit.authentication.AuthenticationManager;
import com.fitbit.authentication.ClientCredentials;
import com.fitbit.authentication.Scope;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

/**
 * Created by jboggess on 9/28/16.
 */

public class FitbitAuthApplication extends Application {

    /**
     * These client credentials come from creating an app on https://dev.fitbit.com.
     * <p>
     * To use with your own app, register as a developer at https://dev.fitbit.com, create an application,
     * set the "OAuth 2.0 Application Type" to "Client", enter a word for the redirect url as a url
     * (like `https://finished` or `https://done` or `https://completed`, etc.), and save.
     * <p>
     * You'll get a client id and client secret which you can enter here. Since these are sensitive
     * values, you'll want to package up into a jks for a production application.
     */

    private static final String CLIENT_ID = "227YZT";
    private static final String CLIENT_SECRET = "86401692efd006045a157f45755000d0";
    private static final String REDIRECT_URL = "https://finished";


    private static final ClientCredentials CLIENT_CREDENTIALS = new ClientCredentials(CLIENT_ID, CLIENT_SECRET, REDIRECT_URL);

    /**
     * This key was generated using the SecureKeyGenerator [java] class. Run as a Java application (not Android!)
     * This key is used to encrypt the authentication token in Android user preferences, so it might
     * be wise to load from a file or not bundle with the apk. If someone decompiles your application
     * apk code they'll have access to this key, and access to your user's authentication token
     */
    private static final String SECURE_KEY = "CVPdQNAT6fBI4rrPLEn9x0+UV84DoqLFiNHpKOPLRW0=";

    /**
     * This method sets up the authentication config needed for
     * successfully connecting to the Fitbit API. Here we include our client credentials,
     * requested scopes, and  where to return after login
     */
    public static AuthenticationConfiguration generateAuthenticationConfiguration(Context context, Class<? extends Activity> mainActivityClass) {

        return new AuthenticationConfigurationBuilder()

                .setClientCredentials(CLIENT_CREDENTIALS)
                .setEncryptionKey(SECURE_KEY)
                .setTokenExpiresIn(2592000L) // 30 days
                .setBeforeLoginActivity(new Intent(context, mainActivityClass))
                .addRequiredScopes(Scope.profile, Scope.settings)
                .addOptionalScopes(Scope.activity)
                .setLogoutOnAuthFailure(true)

                .build();
    }

    /**
     * 1. When the application starts, load our keys and configure the AuthenticationManager
     */
    @Override
    public void onCreate() {
        super.onCreate();
        AuthenticationManager.configure(this, generateAuthenticationConfiguration(this, RootActivity.class));
    }
}
