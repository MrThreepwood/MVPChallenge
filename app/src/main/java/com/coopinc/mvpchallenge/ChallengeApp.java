package com.coopinc.mvpchallenge;

import android.app.Application;
import android.content.Context;

import com.coopinc.mvpchallenge.data.ChallengeApi;
import com.coopinc.mvpchallenge.data.domain.auth.AuthDomain;
import com.coopinc.mvpchallenge.data.domain.auth.IAuthDomain;
import com.coopinc.mvpchallenge.data.domain.character.CharacterDomain;
import com.coopinc.mvpchallenge.data.domain.character.ICharacterDomain;
import com.coopinc.mvpchallenge.data.domain.kingdom.IKingdomDomain;
import com.coopinc.mvpchallenge.data.domain.kingdom.KingdomDomain;
import com.coopinc.mvpchallenge.data.service.auth.AuthService;
import com.coopinc.mvpchallenge.data.service.character.CharacterService;
import com.coopinc.mvpchallenge.data.service.kingdom.KingdomService;

import retrofit.RestAdapter;

public class ChallengeApp extends Application {
    private static Context context;

    private static final String BASE_URL = "https://challenge2015.myriadapps.com/api/v1";

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(BASE_URL).build();
    private static final ChallengeApi CHALLENGE_API = REST_ADAPTER.create(ChallengeApi.class);

    private static final IAuthDomain I_AUTH_DOMAIN = new AuthDomain(CHALLENGE_API);
    private static final IKingdomDomain I_KINGDOM_DOMAIN = new KingdomDomain(CHALLENGE_API);
    private static final ICharacterDomain I_CHARACTER_DOMAIN = new CharacterDomain(CHALLENGE_API);

    private static final AuthService AUTH_SERVICE = new AuthService(I_AUTH_DOMAIN);
    private static final KingdomService KINGDOM_SERVICE = new KingdomService(I_KINGDOM_DOMAIN);
    private static final CharacterService CHARACTER_SERVICE = new CharacterService(I_CHARACTER_DOMAIN);


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
    public static Context getContext() {
        return context;
    }

    public static AuthService getAuthService() {
        return AUTH_SERVICE;
    }

    public static KingdomService getKingdomService() {
        return KINGDOM_SERVICE;
    }

    public static CharacterService getCharacterService() {return CHARACTER_SERVICE;}
}
