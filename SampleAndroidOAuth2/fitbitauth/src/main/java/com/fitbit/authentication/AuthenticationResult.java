package com.fitbit.authentication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jboggess on 9/14/16.
 */
public class AuthenticationResult implements Parcelable {

    public static final Creator<AuthenticationResult> CREATOR = new Creator<AuthenticationResult>() {
        @Override
        public AuthenticationResult createFromParcel(Parcel in) {
            return new AuthenticationResult(in);
        }

        @Override
        public AuthenticationResult[] newArray(int size) {
            return new AuthenticationResult[size];
        }
    };
    private final Status status;
    private final String errorMessage;
    private final AccessToken accessToken;
    private final Set<Scope> missingScopes;

    private AuthenticationResult(Status status, AccessToken accessToken, String errorMessage, Set<Scope> missingScopes) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.accessToken = accessToken;
        this.missingScopes = missingScopes;
    }

    protected AuthenticationResult(Parcel in) {
        status = Status.fromString(in.readString());
        errorMessage = in.readString();

        if (status == Status.successful) {
            accessToken = in.readParcelable(AccessToken.class.getClassLoader());
        } else {
            accessToken = null;
        }

        if (status == Status.missing_required_scopes) {
            List<Scope> scopes = in.createTypedArrayList(Scope.CREATOR);
            this.missingScopes = new HashSet<>(scopes);
        } else {
            this.missingScopes = null;
        }
    }

    public static AuthenticationResult error(String message) {
        return new AuthenticationResult(Status.error, null, message, null);
    }

    public static AuthenticationResult dismissed() {
        return new AuthenticationResult(Status.dismissed, null, null, null);
    }

    public static AuthenticationResult success(AccessToken accessToken) {
        return new AuthenticationResult(Status.successful, accessToken, null, null);
    }

    public static AuthenticationResult missingRequiredScopes(Set<Scope> scopes) {
        return new AuthenticationResult(Status.missing_required_scopes, null, null, scopes);
    }

    public static Creator<AuthenticationResult> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status.name());
        dest.writeString(errorMessage);
        if (status == Status.successful) {
            dest.writeParcelable(this.accessToken, 0);
        } else if (status == Status.missing_required_scopes) {
            List<Scope> scopeList = new ArrayList<>();
            scopeList.addAll(this.missingScopes);
            dest.writeTypedList(scopeList);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isSuccessful() {
        return status == Status.successful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public Set<Scope> getMissingScopes() {
        return missingScopes;
    }

    public enum Status {
        successful, dismissed, error, missing_required_scopes;

        public static Status fromString(String string) {
            for (Status status : values()) {
                if (status.name().equals(string)) {
                    return status;
                }
            }

            return null;
        }
    }
}
