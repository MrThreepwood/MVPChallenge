package com.coopinc.mvpchallenge.data.domain;

import com.coopinc.mvpchallenge.ChallengeApp;
import com.coopinc.mvpchallenge.data.models.KingdomBriefModel;
import com.coopinc.mvpchallenge.data.service.IKingdomListCallback;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class KingdomDomain {

    public void getKingdoms (final IKingdomListCallback resultCallback) {
        ChallengeApp.getApiInstance().getKingdoms(new Callback<List<KingdomBriefModel>>() {
            @Override
            public void success(List<KingdomBriefModel> kingdomBriefModels, Response response) {
                resultCallback.kingdomListSuccess(kingdomBriefModels);
            }

            @Override
            public void failure(RetrofitError error) {
                resultCallback.kingdomListFailure(error.getMessage());
            }
        });

    }
}
