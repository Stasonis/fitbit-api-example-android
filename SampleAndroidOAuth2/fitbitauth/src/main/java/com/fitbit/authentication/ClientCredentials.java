package com.fitbit.authentication;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by jboggess on 9/14/16.
 */
public class ClientCredentials implements Parcelable {
    public static final Creator<ClientCredentials> CREATOR = new Creator<ClientCredentials>() {
        @Override
        public ClientCredentials createFromParcel(Parcel in) {
            return new ClientCredentials(in);
        }

        @Override
        public ClientCredentials[] newArray(int size) {
            return new ClientCredentials[size];
        }
    };
    private String clientId;
    private String clientSecret;
    private String redirectUrl;

    public ClientCredentials(String clientId, String clientSecret, String redirectUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUrl = redirectUrl;
    }

    protected ClientCredentials(Parcel in) {
        clientId = in.readString();
        clientSecret = in.readString();
        redirectUrl = in.readString();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public boolean isComplete() {
        return !TextUtils.isEmpty(clientId)
                && !TextUtils.isEmpty(clientSecret)
                && !TextUtils.isEmpty(redirectUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(clientId);
        parcel.writeString(clientSecret);
        parcel.writeString(redirectUrl);
    }
}
