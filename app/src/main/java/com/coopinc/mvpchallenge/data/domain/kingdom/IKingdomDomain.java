package com.coopinc.mvpchallenge.data.domain.kingdom;

import com.coopinc.mvpchallenge.data.service.kingdom.IKingdomListCallback;

/**
 * Created by joshuaswoyer on 5/12/16.
 */
public interface IKingdomDomain {
    void getKingdoms (IKingdomListCallback resultCallback);
}
