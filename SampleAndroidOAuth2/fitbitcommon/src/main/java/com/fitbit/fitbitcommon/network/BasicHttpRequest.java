package com.fitbit.fitbitcommon.network;

import android.support.v4.util.Pair;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jboggess on 11/30/16.
 */

public class BasicHttpRequest {

    private String url;
    private String authorization;
    private String method;
    private String contentType;
    private byte[] content;
    private boolean useCaches;
    private List<Pair<String, String>> params;

    BasicHttpRequest() {

    }

    public String getUrl() {
        return url;
    }

    void setUrl(String url) {
        this.url = url;
    }

    void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getMethod() {
        return method;
    }

    void setMethod(String method) {
        this.method = method;
    }

    public String getContentType() {
        return contentType;
    }

    void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getContentLength() {
        return content != null ? content.length : 0;
    }

    void setContent(byte[] content) {
        this.content = content;
    }

    void setContent(String content) throws UnsupportedEncodingException {
        setContent(content.getBytes("UTF-8"));
    }

    public boolean useCaches() {
        return useCaches;
    }

    void setUseCaches(boolean useCaches) {
        this.useCaches = useCaches;
    }

    void setParams(List<Pair<String, String>> params) {
        this.params = params;
    }

    public List<Pair<String, String>> getParams() {
        return params;
    }

    private synchronized void fillInConnectionInfo(HttpURLConnection connection) throws IOException {
        connection.setRequestMethod(method);

        if (!TextUtils.isEmpty(authorization)) {
            connection.setRequestProperty("Authorization", authorization);
        }

        if (!TextUtils.isEmpty(contentType)) {
            connection.setRequestProperty("Content-Type", contentType);
        }

        if (content == null || content.length == 0) {
            connection.setRequestProperty("Content-Length", "0");
        } else {
            connection.setRequestProperty("Content-Length", Integer.toString(content.length));
            OutputStream outputStream = null;

            try {
                outputStream = connection.getOutputStream();
                outputStream.write(this.content);
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }
        connection.setUseCaches(this.useCaches);
    }

    private String getQuery(List<Pair<String, String>> params) throws UnsupportedEncodingException {
        List<String> keyValues = new ArrayList<>();

        for (Pair<String, String> pair : params) {
            keyValues.add(
                    URLEncoder.encode(pair.first, "UTF-8")
                            + "="
                            + URLEncoder.encode(pair.second, "UTF-8")
            );
        }

        return TextUtils.join("&", keyValues);
    }

    public byte[] readBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        return byteBuffer.toByteArray();
    }

    public synchronized BasicHttpResponse execute() throws IOException {
        HttpURLConnection connection = null;

        String urlString = this.url + ((params != null) ? "?" + getQuery(this.params) : "");

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            fillInConnectionInfo(connection);
            connection.connect();

            return new BasicHttpResponse(
                    connection.getResponseCode(),
                    readBytes(connection.getInputStream())
            );

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
