package com.fitbit.authentication;

import okhttp3.Request;

/**
 * Created by jboggess on 9/26/16.
 */

public class SimpleRequestSigner implements RequestSigner {

    @Override
    public void signRequest(Request.Builder builder) {
        AccessToken currentAccessToken = AuthenticationManager.getCurrentAccessToken();
        String bearer;
        if (currentAccessToken == null || currentAccessToken.hasExpired()) {
            bearer = "foo";
        } else {
            bearer = currentAccessToken.getAccessToken();
        }
        builder.header("Authorization", String.format("Bearer %s", bearer));
    }
}
