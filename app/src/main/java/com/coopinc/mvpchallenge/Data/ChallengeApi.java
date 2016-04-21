package com.coopinc.mvpchallenge.Data;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface ChallengeApi {
    @FormUrlEncoded
    @POST("/subscribe")
    public void logIn(@Field("email") String email, Callback<MessageModel> message);
//    @GET("/kingdoms")
//    public void getKingdoms(Callback<List<KingdomBriefModel>> responseCallback);
//    @GET("/kingdoms/{id}")
//    public void getKingdomDetails(@Path("id") String kingdomId,Callback<KingdomDetailedModel> responseCallback);
//    @GET("/characters/{id}")
//    public void getCharacter(@Path("id") String characterId, Callback<Models.Character> responseCallback);
}
