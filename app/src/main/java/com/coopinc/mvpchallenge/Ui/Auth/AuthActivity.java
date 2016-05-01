package com.coopinc.mvpchallenge.ui.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.coopinc.mvpchallenge.R;
import com.coopinc.mvpchallenge.ui.BaseActivity;
import com.coopinc.mvpchallenge.ui.kingdoms.KingdomsActivity;

public class AuthActivity extends BaseActivity {
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_auth);
        container = R.id.auth_container;
        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (prefs.contains(LoginPresenter.EMAIL_PREFS_KEY)) {
            Intent intent = new Intent(this, KingdomsActivity.class);
            startActivity(intent);
        } else {
            LoginFragment loginFragment = new LoginFragment();
            getFragmentManager().beginTransaction().add(container, loginFragment).commit();
        }
    }
}
