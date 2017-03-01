package com.fitbit.authentication;

import com.fitbit.fitbitcommon.network.BasicHttpRequestBuilder;

/**
 * Created by jboggess on 9/26/16.
 */

public interface RequestSigner {

    void signRequest(BasicHttpRequestBuilder builder);

}
