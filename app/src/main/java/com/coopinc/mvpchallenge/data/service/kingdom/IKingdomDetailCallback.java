package com.coopinc.mvpchallenge.data.service.kingdom;

import com.coopinc.mvpchallenge.data.models.KingdomModel;

public interface IKingdomDetailCallback {
    void kingdomDetailSuccess(KingdomModel kingdom);
    void kingdomDetailFailure(String error);
}
