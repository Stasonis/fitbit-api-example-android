package com.fitbit.fitbitcommon.network;

import android.support.v4.util.Pair;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by jboggess on 11/30/16.
 */

public class BasicHttpRequestBuilder {

    private BasicHttpRequest basicHttpRequest;

    public static BasicHttpRequestBuilder create() {
        return new BasicHttpRequestBuilder();
    }

    public BasicHttpRequest build() {
        if (TextUtils.isEmpty(basicHttpRequest.getUrl())) {
            throw new IllegalArgumentException("Url cannot be empty!");
        }
        return basicHttpRequest;

    }

    private BasicHttpRequestBuilder() {
        basicHttpRequest = new BasicHttpRequest();
        basicHttpRequest.setMethod("GET");
    }

    public BasicHttpRequestBuilder setUrl(String url) {
        basicHttpRequest.setUrl(url);
        return this;
    }

    public BasicHttpRequestBuilder setAuthorization(String authorization) {
        basicHttpRequest.setAuthorization(authorization);
        return this;
    }

    public BasicHttpRequestBuilder setMethod(String method) {
        basicHttpRequest.setMethod(method);
        return this;
    }

    public BasicHttpRequestBuilder setContentType(String contentType) {
        basicHttpRequest.setContentType(contentType);
        return this;
    }

    public BasicHttpRequestBuilder setContent(String content) throws UnsupportedEncodingException {
        basicHttpRequest.setContent(content);
        return this;
    }

    public BasicHttpRequestBuilder setContent(byte[] content) {
        basicHttpRequest.setContent(content);
        return this;
    }

    public BasicHttpRequestBuilder setUseCaches(boolean useCaches) {
        basicHttpRequest.setUseCaches(useCaches);
        return this;
    }

    public BasicHttpRequestBuilder addQueryParam(String name, String value) {
        if (basicHttpRequest.getParams() == null) {
            basicHttpRequest.setParams(new ArrayList<Pair<String, String>>());
        }

        basicHttpRequest.getParams().add(new Pair<String, String>(name, value));
        return this;
    }
}
