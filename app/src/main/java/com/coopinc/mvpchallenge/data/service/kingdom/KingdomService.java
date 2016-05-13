package com.coopinc.mvpchallenge.data.service.kingdom;

import com.coopinc.mvpchallenge.data.domain.kingdom.IKingdomDomain;
import com.coopinc.mvpchallenge.data.events.KingdomListFailureEvent;
import com.coopinc.mvpchallenge.data.events.KingdomListSuccessEvent;
import com.coopinc.mvpchallenge.data.models.KingdomModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class KingdomService implements IKingdomListCallback {
    private final IKingdomDomain domain;

    public KingdomService(IKingdomDomain domain) {
        this.domain = domain;
    }

    public void getKingdoms() {
        domain.getKingdoms(this);
    }

    @Override
    public void kingdomListSuccess(List<KingdomModel> kingdoms) {
        EventBus.getDefault().post(new KingdomListSuccessEvent(kingdoms));
    }

    @Override
    public void kingdomListFailure(String error) {
        EventBus.getDefault().post(new KingdomListFailureEvent(error));
    }
}
