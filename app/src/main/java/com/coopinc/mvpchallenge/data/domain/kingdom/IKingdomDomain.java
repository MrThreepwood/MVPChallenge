package com.coopinc.mvpchallenge.data.domain.kingdom;

import com.coopinc.mvpchallenge.data.service.kingdom.IKingdomDetailCallback;
import com.coopinc.mvpchallenge.data.service.kingdom.IKingdomListCallback;

public interface IKingdomDomain {
    void getKingdoms (IKingdomListCallback resultCallback);
    void getKingdomDetail (IKingdomDetailCallback resultCallback, String id);
}
