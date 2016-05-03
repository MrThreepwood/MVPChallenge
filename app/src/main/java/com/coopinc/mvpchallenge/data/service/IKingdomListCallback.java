package com.coopinc.mvpchallenge.data.service;

import com.coopinc.mvpchallenge.data.models.KingdomBriefModel;

import java.util.List;

public interface IKingdomListCallback {

    void kingdomListSuccess(List<KingdomBriefModel> kingdoms);

    void kingdomListFailure(String error);
}
