package com.coopinc.mvpchallenge.Ui.Auth;

public interface LoginViewInterface {
    void showLoadingIndicator();
    void hideLoadingIndicator();
    void setNameError(String error);
    void setEmailError(String error);
    void showOtherError(String error);
}
