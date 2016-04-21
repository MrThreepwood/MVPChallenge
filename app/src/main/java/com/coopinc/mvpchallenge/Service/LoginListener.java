package com.coopinc.mvpchallenge.Service;

import com.coopinc.mvpchallenge.Data.MessageModel;

/**
 * Created by joshuaswoyer on 4/20/16.
 */
public interface LoginListener {
    void logInSuccess(MessageModel message);

    void logInFailure(String error);
}
