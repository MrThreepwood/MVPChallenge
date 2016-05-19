package com.coopinc.mvpchallenge.data;

import com.coopinc.mvpchallenge.data.models.CharacterModel;
import com.coopinc.mvpchallenge.data.models.KingdomModel;
import com.coopinc.mvpchallenge.data.models.MessageModel;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ChallengeApi {
    @FormUrlEncoded
    @POST("/subscribe")
    void logIn(@Field("email") String email, Callback<MessageModel> message);

    @GET("/kingdoms")
    void getKingdoms(Callback<List<KingdomModel>> responseCallback);

    @GET("/kingdoms/{id}")
    void getKingdomDetail(@Path("id") String kingdomId,Callback<KingdomModel> responseCallback);

    @GET("/characters/{id}")
    void getCharacter(@Path("id") String characterId, Callback<CharacterModel> responseCallback);
}
