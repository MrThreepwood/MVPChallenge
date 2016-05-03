package com.coopinc.mvpchallenge.ui;

import org.greenrobot.eventbus.EventBus;

public class BasePresenter {

    public void onPause() {
        EventBus.getDefault().unregister(this);
    }

    public void onResume() {
        EventBus.getDefault().register(this);
    }
}
