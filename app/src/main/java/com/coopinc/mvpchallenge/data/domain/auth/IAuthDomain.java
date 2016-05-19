package com.coopinc.mvpchallenge.data.domain.auth;

import com.coopinc.mvpchallenge.data.service.auth.ILoginCallback;


public interface IAuthDomain {
    void logIn(String email, ILoginCallback resultCallback);
}
