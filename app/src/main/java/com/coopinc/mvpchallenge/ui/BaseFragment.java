package com.coopinc.mvpchallenge.ui;

import android.app.Fragment;
import android.os.Bundle;

import butterknife.ButterKnife;
import icepick.Icepick;

public abstract class BaseFragment<T extends IBasePresenter> extends Fragment{

    public T presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = onCreatePresenter();
        Icepick.restoreInstanceState(this, savedInstanceState);
        presenter.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
        presenter.onSavedInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public abstract T onCreatePresenter();
}
