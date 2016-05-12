package com.coopinc.mvpchallenge.ui;

import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

import icepick.Icepick;

public abstract class BasePresenter implements IBasePresenter {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onSavedInstanceState(Bundle outState) {
        Icepick.saveInstanceState(this, outState);
    }
}
