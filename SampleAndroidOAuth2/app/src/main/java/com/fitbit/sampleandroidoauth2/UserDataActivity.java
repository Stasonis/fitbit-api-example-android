package com.fitbit.sampleandroidoauth2;

import com.fitbit.api.MissingScopesException;
import com.fitbit.api.ResourceLoadedHandler;
import com.fitbit.api.models.Device;
import com.fitbit.api.models.User;
import com.fitbit.api.services.ActivityService;
import com.fitbit.api.services.DeviceService;
import com.fitbit.api.services.UserService;
import com.fitbit.authentication.AccessToken;
import com.fitbit.authentication.AuthenticationManager;
import com.fitbit.authentication.Scope;
import com.fitbit.sampleandroidoauth2.databinding.ActivityUserDataBinding;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.common.base.Joiner;

import com.fitbit.api.models.Activities;

public class UserDataActivity extends AppCompatActivity implements UserService.UserHandler, ResourceLoadedHandler {

    private ActivityUserDataBinding binding;

    public static Intent newIntent(Context context) {
        return new Intent(context, UserDataActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_data);
    }

    @Override
    protected void onResume() {
        super.onResume();

        binding.setLoading(true);
        displayScopes();
        loadUserData();
    }

    private void displayScopes() {
        AccessToken accessToken = AuthenticationManager.getCurrentAccessToken();
        binding.setScopesGranted(Joiner.on(", ").join(accessToken.getScopes()));
    }

    private void loadUserData() {
        try {
            UserService.getLoggedInUserProfile(this, this);
            DeviceService.getUserDevices(this, this);

            if (AuthenticationManager.getCurrentAccessToken().getScopes().contains(Scope.activity)) {
                ActivityService.getUserActivities(this, this);
            }
        } catch (MissingScopesException e) {
            Toast.makeText(this,
                    String.format(getString(R.string.scopes_missing_format), Joiner.on("`, `").join(e.getScopes())),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onLogoutClick(View view) {
        binding.setLoading(true);
        AuthenticationManager.logout(this);
    }


    @Override
    public void onUserLoaded(User user) {
        binding.setUser(user);
        binding.setLoading(false);
        binding.profileInfoView.bindProfileInfo(user);
    }
    @Override
    public void onErrorLoadingUser(String errorMessage) {
        showErrorMesage(errorMessage);
    }

    @Override
    public void onResourceLoaded(Object resource) {
        if (resource instanceof Device[]) {
            binding.deviceInfoView.bindDevices((Device[])resource);
        } else if (resource instanceof Activities) {
            binding.activityInfoView.bindActivityData((Activities)resource);
        }
    }

    @Override
    public void onResourceLoadError(String errorMessage) {
        showErrorMesage(errorMessage);
    }

    private void showErrorMesage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
