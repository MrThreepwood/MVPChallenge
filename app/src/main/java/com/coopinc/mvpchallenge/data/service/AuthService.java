package com.coopinc.mvpchallenge.data.service;

import com.coopinc.mvpchallenge.data.domain.AuthDomain;
import com.coopinc.mvpchallenge.data.events.LoginFailEvent;
import com.coopinc.mvpchallenge.data.events.LoginSuccessEvent;
import com.coopinc.mvpchallenge.data.models.MessageModel;

import org.greenrobot.eventbus.EventBus;


public class AuthService implements ILoginCallback {
    private final AuthDomain authDomain;

    public AuthService (AuthDomain domain) {
        authDomain = domain;
    }
    public void logIn(String email) {
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
