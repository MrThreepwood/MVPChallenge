package com.coopinc.mvpchallenge.ui;

import android.app.Fragment;

public interface IBaseActivity {
    void nextFragment(Fragment fragment);

    void nextActivity(Class activityClass);
}
