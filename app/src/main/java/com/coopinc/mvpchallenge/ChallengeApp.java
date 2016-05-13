package com.coopinc.mvpchallenge;

import android.app.Application;
import android.content.Context;

import com.coopinc.mvpchallenge.data.ChallengeApi;
import com.coopinc.mvpchallenge.data.domain.auth.AuthDomain;
import com.coopinc.mvpchallenge.data.domain.auth.IAuthDomain;
import com.coopinc.mvpchallenge.data.domain.kingdom.IKingdomDomain;
import com.coopinc.mvpchallenge.data.domain.kingdom.KingdomDomain;
import com.coopinc.mvpchallenge.data.service.auth.AuthService;
import com.coopinc.mvpchallenge.data.service.kingdom.KingdomService;

import retrofit.RestAdapter;

public class ChallengeApp extends Application {
    private static Context context;

    private static final String BASE_URL = "https://challenge2015.myriadapps.com/api/v1";

    private static final RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(BASE_URL).build();
    private static final ChallengeApi apiInstance = restAdapter.create(ChallengeApi.class);

    private static final IAuthDomain authDomain = new AuthDomain(apiInstance);
    private static final IKingdomDomain kingdomDomain = new KingdomDomain(apiInstance);

    private static final AuthService authService = new AuthService(authDomain);
    private static final KingdomService kingdomService = new KingdomService(kingdomDomain);


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
    public static Context getContext() {
        return context;
    }

    public static AuthService getAuthService() {
        return authService;
    }

    public static KingdomService getKingdomService() {
        return kingdomService;
    }
}
