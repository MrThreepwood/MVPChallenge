package com.coopinc.mvpchallenge.ui.auth;

public interface ILoginView {
    void showLoadingIndicator();
    void hideLoadingIndicator();
    void setNameError(String error);
    void setEmailError(String error);
    void showOtherError(String error);
}
