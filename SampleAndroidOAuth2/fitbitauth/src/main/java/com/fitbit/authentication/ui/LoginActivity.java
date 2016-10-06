package com.fitbit.authentication.ui;

import com.fitbit.authentication.AuthenticationHandler;
import com.fitbit.authentication.AuthenticationResult;
import com.fitbit.authentication.AuthorizationController;
import com.fitbit.authentication.ClientCredentials;
import com.fitbit.authentication.Scope;
import com.fitbit.fitbitauth.R;
import com.fitbit.fitbitauth.databinding.ActivityLoginBinding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;


public class LoginActivity extends AppCompatActivity implements AuthenticationHandler {

    public static final String CONFIGURATION_VERSION = "CONFIGURATION_VERSION";
    public static final String AUTHENTICATION_RESULT_KEY = "AUTHENTICATION_RESULT_KEY";
    private static final String CLIENT_CREDENTIALS_KEY = "CLIENT_CREDENTIALS_KEY";
    private static final String EXPIRES_IN_KEY = "EXPIRES_IN_KEY";
    private static final String SCOPES_KEY = "SCOPES_KEY";
    private ActivityLoginBinding binding;

    public static Intent createIntent(Context context, @NonNull ClientCredentials clientCredentials, @Nullable Long expiresIn, Set<Scope> scopes) {
        return createIntent(context, null, clientCredentials, expiresIn, scopes);
    }

    public static Intent createIntent(Context context, Integer configVersion, @NonNull ClientCredentials clientCredentials, @Nullable Long expiresIn, Set<Scope> scopes) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra(CLIENT_CREDENTIALS_KEY, clientCredentials);
        intent.putExtra(CONFIGURATION_VERSION, configVersion);
        intent.putExtra(EXPIRES_IN_KEY, expiresIn);
        intent.putExtra(SCOPES_KEY, scopes.toArray(new Scope[scopes.size()]));

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        ClientCredentials clientCredentials = getIntent().getParcelableExtra(CLIENT_CREDENTIALS_KEY);
        Long expiresIn = getIntent().getLongExtra(EXPIRES_IN_KEY, 604800);
        Parcelable[] scopes = getIntent().getParcelableArrayExtra(SCOPES_KEY);
        Set<Scope> scopesSet = new HashSet<>();
        for (Parcelable scope : scopes) {
            scopesSet.add((Scope) scope);
        }

        AuthorizationController authorizationController = new AuthorizationController(
                binding.loginWebview,
                clientCredentials,
                this);

        authorizationController.authenticate(expiresIn, scopesSet);

    }

    @Override
    public void onAuthFinished(AuthenticationResult result) {
        binding.loginWebview.setVisibility(View.GONE);

        Intent resultIntent = new Intent();
        resultIntent.putExtra(AUTHENTICATION_RESULT_KEY, result);
        resultIntent.putExtra(CONFIGURATION_VERSION, getIntent().getIntExtra(CONFIGURATION_VERSION, 0));
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
