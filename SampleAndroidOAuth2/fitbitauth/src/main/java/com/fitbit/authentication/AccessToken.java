package com.fitbit.authentication;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jboggess on 9/14/16.
 */
public class AccessToken implements Parcelable {

    public static final Creator<AccessToken> CREATOR = new Creator<AccessToken>() {
        @Override
        public AccessToken createFromParcel(Parcel in) {
            return new AccessToken(in);
        }

        @Override
        public AccessToken[] newArray(int size) {
            return new AccessToken[size];
        }
    };
    private String accessToken;
    private Long expirationDate;
    private List<Scope> scopes = new ArrayList<>();

    public AccessToken(String accessToken, Long expirationDate, List<Scope> scopes) {
        this.accessToken = accessToken;
        this.expirationDate = expirationDate;
        this.scopes = scopes;
    }

    protected AccessToken(Parcel in) {
        accessToken = in.readString();
        expirationDate = in.readLong();
        scopes = in.createTypedArrayList(Scope.CREATOR);
    }

    public static AccessToken fromBase64(String base64String) {
        if (base64String == null) {
            return null;
        }
        byte[] serializedMe = Base64.decode(base64String, 0);
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(serializedMe, 0, serializedMe.length);
        parcel.setDataPosition(0); // This is extremely important!

        AccessToken accessToken = new AccessToken(parcel);
        parcel.recycle();
        return accessToken;
    }

    public boolean hasExpired() {
        return expirationDate < System.currentTimeMillis() / 1000;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<Scope> getScopes() {
        return scopes;
    }

    public void setScopes(List<Scope> scopes) {
        this.scopes = scopes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(accessToken);
        parcel.writeLong(expirationDate);
        parcel.writeTypedList(scopes);
    }

    public String toBase64String() {
        Parcel parcel = Parcel.obtain();
        this.writeToParcel(parcel, 0);
        byte[] serializedMe = parcel.marshall();
        parcel.recycle();
        return Base64.encodeToString(serializedMe, 0);
    }
}
