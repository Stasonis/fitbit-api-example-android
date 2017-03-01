package com.fitbit.sampleandroidoauth2.fragments;


import com.fitbit.api.loaders.ResourceLoaderResult;
import com.fitbit.api.models.Device;
import com.fitbit.api.services.DeviceService;
import com.fitbit.sampleandroidoauth2.R;

import android.content.Loader;
import android.os.Bundle;

/**
 * Created by jboggess on 10/17/16.
 */

public class DeviceFragment extends InfoFragment<Device[]> {
    @Override
    public int getTitleResourceId() {
        return R.string.devices;
    }

    @Override
    protected int getLoaderId() {
        return 2;
    }

    @Override
    public Loader<ResourceLoaderResult<Device[]>> onCreateLoader(int id, Bundle args) {
        return DeviceService.getUserDevicesLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ResourceLoaderResult<Device[]>> loader, ResourceLoaderResult<Device[]> data) {
        super.onLoadFinished(loader, data);
        if (data.isSuccessful()) {
            bindDevices(data.getResult());
        }
    }

    public void bindDevices(Device[] devices) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Device device : devices) {
            stringBuilder.append("<b>FITBIT ");
            stringBuilder.append(device.getDeviceVersion().toUpperCase());
            stringBuilder.append("&trade;</b><br>");

            stringBuilder.append("<b>&nbsp;&nbsp;Type: </b>");
            stringBuilder.append(device.getType().toLowerCase());
            stringBuilder.append("<br>");

            stringBuilder.append("<b>&nbsp;&nbsp;Last Sync: </b>");
            stringBuilder.append(device.getLastSyncTime());
            stringBuilder.append("<br>");

            stringBuilder.append("<b>&nbsp;&nbsp;Battery Level: </b>");
            stringBuilder.append(device.getBattery());
            stringBuilder.append("<br><br>");
        }

        if (stringBuilder.length() > 0) {
            stringBuilder.replace(stringBuilder.length() - 8, stringBuilder.length(), "");
        }

        setMainText(stringBuilder.toString());
    }

}
