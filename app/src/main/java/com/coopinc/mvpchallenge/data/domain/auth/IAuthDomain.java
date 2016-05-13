package com.coopinc.mvpchallenge.data.domain.auth;

import com.coopinc.mvpchallenge.data.service.auth.ILoginCallback;

/**
 * Created by joshuaswoyer on 5/12/16.
 */
public interface IAuthDomain {
    void logIn(String email, ILoginCallback resultCallback);
}
