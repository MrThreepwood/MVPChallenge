package com.coopinc.mvpchallenge.ui.auth;

public interface ILoginPresenter {
    void onPause();
    void onResume();
    void logIn(String name, String email);
}
