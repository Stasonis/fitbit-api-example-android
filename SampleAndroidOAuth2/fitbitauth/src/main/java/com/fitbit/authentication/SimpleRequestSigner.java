package com.fitbit.authentication;

import com.fitbit.fitbitcommon.network.BasicHttpRequestBuilder;

/**
 * Created by jboggess on 9/26/16.
 */

public class SimpleRequestSigner implements RequestSigner {

    @Override
    public void signRequest(BasicHttpRequestBuilder builder) {
        AccessToken currentAccessToken = AuthenticationManager.getCurrentAccessToken();
        String bearer;
        if (currentAccessToken == null || currentAccessToken.hasExpired()) {
            bearer = "foo";
        } else {
            bearer = currentAccessToken.getAccessToken();
        }
        builder.setAuthorization(String.format("Bearer %s", bearer));
    }
}
