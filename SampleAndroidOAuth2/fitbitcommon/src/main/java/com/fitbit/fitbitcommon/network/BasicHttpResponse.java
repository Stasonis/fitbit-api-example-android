package com.fitbit.fitbitcommon.network;

import java.io.UnsupportedEncodingException;

/**
 * Created by jboggess on 11/30/16.
 */

public class BasicHttpResponse {

    private byte[] body;
    private int statusCode;

    public BasicHttpResponse() {
    }

    public BasicHttpResponse(int statusCode, byte[] body) {
        this.body = body;
        this.statusCode = statusCode;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBodyAsString() throws UnsupportedEncodingException {
        return new String(this.body, "UTF-8");
    }

    public boolean isSuccessful() {
        return statusCode >= 100 && statusCode < 400;
    }
}
