package com.coopinc.mvpchallenge.Service;

import com.coopinc.mvpchallenge.ChallengeApp;
import com.coopinc.mvpchallenge.Data.Events.LoginFailEvent;
import com.coopinc.mvpchallenge.Data.Events.LoginSuccessEvent;
import com.coopinc.mvpchallenge.Data.MessageModel;
import com.coopinc.mvpchallenge.Domain.AuthDomain;

import org.greenrobot.eventbus.EventBus;


public class AuthService implements LoginListener {
    private AuthDomain authDomain;
    public void logIn(String email) {
        if (authDomain == null) {
            authDomain = ChallengeApp.getAuthDomain();
        }
        authDomain.logIn(email, this);
    }

    @Override
    public void logInSuccess(MessageModel message) {
        EventBus.getDefault().post(new LoginSuccessEvent(message));
    }

    @Override
    public void logInFailure(String error) {
        EventBus.getDefault().post(new LoginFailEvent(error));
    }
}
