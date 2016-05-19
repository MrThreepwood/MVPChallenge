package com.coopinc.mvpchallenge.ui.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.coopinc.mvpchallenge.R;
import com.coopinc.mvpchallenge.ui.BaseActivity;
import com.coopinc.mvpchallenge.ui.kingdom_list.KingdomsActivity;

public class AuthActivity extends BaseActivity {
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_auth);
        if (savedInstanceState == null) {
            container = R.id.auth_container;
            prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            if (prefs.contains(LoginPresenter.EMAIL_PREFS_KEY)) {
                Intent intent = new Intent(this, KingdomsActivity.class);
                startActivity(intent);
                finish();
            } else {
                LoginFragment loginFragment = new LoginFragment();
                getFragmentManager().beginTransaction().add(container, loginFragment).commit();
            }
        }
    }
}
