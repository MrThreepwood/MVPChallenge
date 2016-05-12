package com.coopinc.mvpchallenge.data.domain;

import com.coopinc.mvpchallenge.data.ChallengeApi;
import com.coopinc.mvpchallenge.data.models.MessageModel;
import com.coopinc.mvpchallenge.data.service.auth.ILoginCallback;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AuthDomain {
    private ChallengeApi api;

    public AuthDomain(ChallengeApi api) {
        this.api = api;
    }

    public void logIn(String email, final ILoginCallback resultCallback) {
        api.logIn(email, new Callback<MessageModel>() {
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

