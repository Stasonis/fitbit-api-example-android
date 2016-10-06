package com.fitbit.authentication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jboggess on 9/14/16.
 */
public enum Scope implements Parcelable {
    activity, heartrate, location, nutrition, profile, settings, sleep, social,
    weight;

    public static final Creator<Scope> CREATOR = new Creator<Scope>() {
        @Override
        public Scope createFromParcel(final Parcel source) {
            return Scope.fromString(source.readString());
        }

        @Override
        public Scope[] newArray(final int size) {
            return new Scope[size];
        }
    };

    public static Set<Scope> all() {
        return new HashSet<>(Arrays.asList(values()));
    }

    public static Scope fromString(String name) {
        for (Scope scope : values()) {
            if (scope.name().equals(name)) {
                return scope;
            }
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(name());
    }
}
