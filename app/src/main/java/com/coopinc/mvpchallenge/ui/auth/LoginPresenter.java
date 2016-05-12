package com.coopinc.mvpchallenge.ui.auth;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.coopinc.mvpchallenge.ChallengeApp;
import com.coopinc.mvpchallenge.R;
import com.coopinc.mvpchallenge.data.events.LoginFailEvent;
import com.coopinc.mvpchallenge.data.events.LoginSuccessEvent;
import com.coopinc.mvpchallenge.data.service.auth.AuthService;
import com.coopinc.mvpchallenge.ui.BasePresenter;
import com.coopinc.mvpchallenge.ui.kingdoms.KingdomsActivity;

import org.greenrobot.eventbus.Subscribe;

public class LoginPresenter extends BasePresenter implements ILoginPresenter {
    public static final String EMAIL_PREFS_KEY = "email";

    private ILoginView view;
    private AuthService authService;

    private String email;

    public LoginPresenter (ILoginView view) {
        this.view = view;
        authService = ChallengeApp.getAuthService();
    }

    @Override
    public void logIn(String name, String email) {
        if (this.email == null) {
            if (validate(name, email)) {
                this.email = email;
                view.showLoadingIndicator();
                authService.logIn(email);
            }
        }
    }

    @Subscribe
    public void onLoginSuccess(LoginSuccessEvent message) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ChallengeApp.getContext());
        prefs.edit().putString(EMAIL_PREFS_KEY, email).commit();
        email = null;
        view.goToKingdomList(KingdomsActivity.class);
    }

    @Subscribe
    public void onLoginFail(LoginFailEvent error) {
        email = null;
        view.hideLoadingIndicator();
        view.showOtherError(error.getError());
    }

    private boolean validate (String name, String email) {
        boolean valid = true;
        if(TextUtils.isEmpty(name)) {
            valid = false;
            view.setNameError(ChallengeApp.getContext().getString(R.string.no_name));
        }
        if(TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            valid = false;
            view.setEmailError(ChallengeApp.getContext().getString(R.string.invalid_email));
        }
        return valid;
    }
}
