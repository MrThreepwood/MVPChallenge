package com.coopinc.mvpchallenge.data.service;

import com.coopinc.mvpchallenge.data.models.MessageModel;

/**
 * Created by joshuaswoyer on 4/20/16.
 */
public interface ILoginCallback {
    void logInSuccess(MessageModel message);

    void logInFailure(String error);
}
