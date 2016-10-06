package com.fitbit.authentication;

import okhttp3.Request;

/**
 * Created by jboggess on 9/26/16.
 */

public interface RequestSigner {

    void signRequest(Request.Builder builder);

}
