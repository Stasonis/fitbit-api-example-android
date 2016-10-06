package com.fitbit.authentication;

/**
 * Created by jboggess on 9/14/16.
 */
public interface AuthenticationHandler {

    void onAuthFinished(AuthenticationResult result);

}
