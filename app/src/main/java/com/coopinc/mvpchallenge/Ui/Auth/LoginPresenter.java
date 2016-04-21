package com.coopinc.mvpchallenge.Ui.Auth;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.coopinc.mvpchallenge.BaseActivity;
import com.coopinc.mvpchallenge.ChallengeApp;
import com.coopinc.mvpchallenge.Data.Events.LoginFailEvent;
import com.coopinc.mvpchallenge.Data.Events.LoginSuccessEvent;
import com.coopinc.mvpchallenge.R;
import com.coopinc.mvpchallenge.Service.AuthService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LoginPresenter implements LoginPresenterInterface {
    private static final String EMAIL_PREFS_KEY = "email";

    private LoginViewInterface view;
    private BaseActivity viewParent;
    private AuthService authService;

    private String email;

    public LoginPresenter (LoginViewInterface view, BaseActivity baseActivity) {
        this.view = view;
        authService = ChallengeApp.getAuthService();
        viewParent = baseActivity;
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
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(viewParent);
        prefs.edit().putString(EMAIL_PREFS_KEY, email).commit();
        email = null;
        Toast.makeText(viewParent, "Tada! Transition after login.", Toast.LENGTH_LONG).show();
        //viewParent.nextFragment(fragment);
    }

    @Subscribe
    public void onLoginFail(LoginFailEvent error) {
        email = null;
        view.hideLoadingIndicator();
        view.showOtherError(error.getError());
    }
    private boolean validate (String name, String email) {
        boolean valid = true;
        if(name == null || name.isEmpty()) {
            valid = false;
            view.setNameError(ChallengeApp.getContext().getString(R.string.no_name));
        }
        if(email == null || email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            valid = false;
            view.setEmailError(ChallengeApp.getContext().getString(R.string.invalid_email));
        }
        return valid;
    }
}
