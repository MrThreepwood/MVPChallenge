package com.coopinc.mvpchallenge.Domain;

import com.coopinc.mvpchallenge.ChallengeApp;
import com.coopinc.mvpchallenge.Data.MessageModel;
import com.coopinc.mvpchallenge.Service.LoginListener;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AuthDomain {

    public void logIn(String email, final LoginListener resultListener) {
        ChallengeApp.getApiInstance().logIn(email, new Callback<MessageModel>() {
            @Override
            public void success(MessageModel messageModel, Response response) {
                if (response.getStatus() == 200) {
                    resultListener.logInSuccess(messageModel);
                } else {
                    resultListener.logInFailure(null);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                resultListener.logInFailure(error.getMessage());
            }
        });
    }
}

