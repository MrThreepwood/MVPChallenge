package com.coopinc.mvpchallenge.ui.kingdoms;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.coopinc.mvpchallenge.ChallengeApp;
import com.coopinc.mvpchallenge.R;
import com.coopinc.mvpchallenge.ui.BaseActivity;
import com.coopinc.mvpchallenge.ui.auth.AuthActivity;
import com.coopinc.mvpchallenge.ui.auth.LoginPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class KingdomsActivity extends BaseActivity {
    private static final String DEFAULT_TITLE = "email";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_kingdom);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
        toolbar.setOnMenuItemClickListener(new MenuItemOnClickListener());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ChallengeApp.getContext());
        getSupportActionBar().setTitle(prefs.getString(LoginPresenter.EMAIL_PREFS_KEY, DEFAULT_TITLE));
        if (savedInstanceState == null) {
            container = R.id.container_kingdom;
            KingdomListFragment listFragment = new KingdomListFragment();
            getFragmentManager().beginTransaction().add(container, listFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    public class MenuItemOnClickListener implements Toolbar.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (getString(R.string.logout).contentEquals(menuItem.getTitle())) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ChallengeApp.getContext());
                prefs.edit().remove(LoginPresenter.EMAIL_PREFS_KEY).commit();
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStackImmediate();
                }
                nextActivity(AuthActivity.class);
                return true;
            }
            return false;
        }
    }
}
