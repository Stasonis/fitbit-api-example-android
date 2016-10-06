package com.fitbit.api.services;

import com.fitbit.api.APIUtils;
import com.fitbit.api.MissingScopesException;
import com.fitbit.api.ResourceLoadedHandler;
import com.fitbit.api.ResourceLoader;
import com.fitbit.api.TokenExpiredException;
import com.fitbit.api.models.Device;
import com.fitbit.authentication.AuthenticationManager;
import com.fitbit.authentication.Scope;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * Created by jboggess on 9/14/16.
 */
public class DeviceService {

    private final static String DEVICE_URL = "https://api.fitbit.com/1/user/-/devices.json";
    private static final ResourceLoader<Device[]> USER_DEVICES_LOADER = new ResourceLoader<>(DEVICE_URL, Device[].class);

    public static void getUserDevices(Activity activityContext, @NonNull final ResourceLoadedHandler<Device[]> devicesLoadedHandler) throws MissingScopesException, TokenExpiredException {
        APIUtils.validateToken(activityContext, AuthenticationManager.getCurrentAccessToken(), Scope.settings);
        USER_DEVICES_LOADER.loadResource(activityContext, devicesLoadedHandler);
    }

}
