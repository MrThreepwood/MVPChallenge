package com.coopinc.mvpchallenge.ui;

import android.os.Bundle;

public interface IBasePresenter {
    void onCreate(Bundle savedInstanceState);
    void onPause();
    void onResume();
    void onSavedInstanceState(Bundle outState);
}
