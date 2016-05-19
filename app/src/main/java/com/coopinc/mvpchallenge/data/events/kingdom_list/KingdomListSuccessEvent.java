package com.coopinc.mvpchallenge.data.events.kingdom_list;

import com.coopinc.mvpchallenge.data.models.KingdomModel;

import java.util.List;

public class KingdomListSuccessEvent {
    private List<KingdomModel> kindoms;

    public KingdomListSuccessEvent(List<KingdomModel> kindoms) {
        this.kindoms = kindoms;
    }

    public List<KingdomModel> getKindoms() {
        return kindoms;
    }
}
