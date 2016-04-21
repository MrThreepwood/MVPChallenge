package com.coopinc.mvpchallenge.Ui.Auth;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.coopinc.mvpchallenge.BaseActivity;
import com.coopinc.mvpchallenge.R;

public class AuthActivity extends BaseActivity {
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_auth);
        container = R.id.auth_container;
        if (savedInstanceState != null) {
            return;
        }
        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if (prefs.contains("login")) {
//            KingdomListFragment kingdomsFragment = new KingdomListFragment();
//            getFragmentManager().beginTransaction().add(R.id.fragment_container, kingdomsFragment).commit();
        } else {
            LoginFragment loginFragment = new LoginFragment();
            getFragmentManager().beginTransaction().add(R.id.auth_container, loginFragment).commit();
        }
    }

}
