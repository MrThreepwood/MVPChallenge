package com.coopinc.mvpchallenge.data.domain.kingdom;

import com.coopinc.mvpchallenge.data.ChallengeApi;
import com.coopinc.mvpchallenge.data.models.KingdomModel;
import com.coopinc.mvpchallenge.data.service.kingdom.IKingdomListCallback;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class KingdomDomain implements IKingdomDomain {
    private ChallengeApi api;

    public KingdomDomain (ChallengeApi api) {
        this.api = api;
    }
    @Override
    public void getKingdoms(final IKingdomListCallback resultCallback) {
        api.getKingdoms(new Callback<List<KingdomModel>>() {
            @Override
            public void success(List<KingdomModel> kingdomModels, Response response) {
                resultCallback.kingdomListSuccess(kingdomModels);
            }

            @Override
            public void failure(RetrofitError error) {
                resultCallback.kingdomListFailure(error.getMessage());
            }
        });

    }
}
