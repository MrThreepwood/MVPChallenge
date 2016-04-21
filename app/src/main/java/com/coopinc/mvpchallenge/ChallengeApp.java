package com.coopinc.mvpchallenge;

import android.app.Application;
import android.content.Context;

import com.coopinc.mvpchallenge.Data.ChallengeApi;
import com.coopinc.mvpchallenge.Domain.AuthDomain;
import com.coopinc.mvpchallenge.Service.AuthService;

import retrofit.RestAdapter;

public class ChallengeApp extends Application {
    private static Context context;
    private static final AuthDomain authDomain = new AuthDomain();
    private static final AuthService authService = new AuthService();
    private static RestAdapter restAdapter;
    private static ChallengeApi apiInstance;

    private static final String BASE_URL = "https://challenge2015.myriadapps.com/api/v1";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(BASE_URL).build();
    }
    public static Context getContext() {
        return context;
    }

    public static ChallengeApi getApiInstance() {
        if (apiInstance == null) {
            apiInstance = restAdapter.create(ChallengeApi.class);
        }
        return apiInstance;
    }

    public static AuthDomain getAuthDomain() {
        return authDomain;
    }

    public static AuthService getAuthService() {
        return authService;
    }
}
