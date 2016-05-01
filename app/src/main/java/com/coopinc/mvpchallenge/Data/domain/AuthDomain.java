package com.coopinc.mvpchallenge.data.domain;

import com.coopinc.mvpchallenge.ChallengeApp;
import com.coopinc.mvpchallenge.data.models.MessageModel;
import com.coopinc.mvpchallenge.data.service.ILoginCallback;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AuthDomain {

    public void logIn(String email, final ILoginCallback resultCallback) {
        ChallengeApp.getApiInstance().logIn(email, new Callback<MessageModel>() {
            @Override
            public void success(MessageModel messageModel, Response response) {
                resultCallback.logInSuccess(messageModel);
            }

            @Override
            public void failure(RetrofitError error) {
                resultCallback.logInFailure(error.getMessage());
            }
        });
    }
}

