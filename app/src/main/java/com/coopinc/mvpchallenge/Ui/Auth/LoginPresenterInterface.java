package com.coopinc.mvpchallenge.Ui.Auth;

public interface LoginPresenterInterface {
    void onPause();
    void onResume();
    void logIn(String name, String email);
}
