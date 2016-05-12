package com.coopinc.mvpchallenge.data.service.auth;

import com.coopinc.mvpchallenge.data.models.MessageModel;

public interface ILoginCallback {
    void logInSuccess(MessageModel message);

    void logInFailure(String error);
}
