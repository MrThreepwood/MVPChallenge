package com.coopinc.mvpchallenge.data.service.kingdom;

import com.coopinc.mvpchallenge.data.domain.kingdom.IKingdomDomain;
import com.coopinc.mvpchallenge.data.events.kingdom_detail.KingdomDetailFailureEvent;
import com.coopinc.mvpchallenge.data.events.kingdom_detail.KingdomDetailSuccessEvent;
import com.coopinc.mvpchallenge.data.events.kingdom_list.KingdomListFailureEvent;
import com.coopinc.mvpchallenge.data.events.kingdom_list.KingdomListSuccessEvent;
import com.coopinc.mvpchallenge.data.models.KingdomModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class KingdomService implements IKingdomListCallback, IKingdomDetailCallback {
    private final IKingdomDomain domain;

    public KingdomService(IKingdomDomain domain) {
        this.domain = domain;
    }

    public void getKingdoms() {
        domain.getKingdoms(this);
    }

    public void getKingdomDetail(String id) {
        domain.getKingdomDetail(this, id);
    }

    @Override
    public void kingdomListSuccess(List<KingdomModel> kingdoms) {
        EventBus.getDefault().post(new KingdomListSuccessEvent(kingdoms));
    }

    @Override
    public void kingdomListFailure(String error) {
        EventBus.getDefault().post(new KingdomListFailureEvent(error));
    }

    @Override
    public void kingdomDetailSuccess(KingdomModel kingdom) {
        EventBus.getDefault().post(new KingdomDetailSuccessEvent(kingdom));
    }

    @Override
    public void kingdomDetailFailure(String error) {
        EventBus.getDefault().post(new KingdomDetailFailureEvent(error));
    }
}
