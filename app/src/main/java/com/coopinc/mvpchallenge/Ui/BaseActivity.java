package com.coopinc.mvpchallenge.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity implements IBaseActivity {

    protected int container;

    @Override
    public void nextFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(container, fragment).commit();
    }

    @Override
    public void nextActivity(Class activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }
}
