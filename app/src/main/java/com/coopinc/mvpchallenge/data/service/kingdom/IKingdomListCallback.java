package com.coopinc.mvpchallenge.data.service.kingdom;

import com.coopinc.mvpchallenge.data.models.KingdomModel;

import java.util.List;

public interface IKingdomListCallback {
    void kingdomListSuccess(List<KingdomModel> kingdoms);
    void kingdomListFailure(String error);
}
