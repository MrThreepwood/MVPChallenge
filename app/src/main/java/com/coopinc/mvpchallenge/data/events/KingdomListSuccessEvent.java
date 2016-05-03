package com.coopinc.mvpchallenge.data.events;

import com.coopinc.mvpchallenge.data.models.KingdomBriefModel;

import java.util.List;

public class KingdomListSuccessEvent {
    private List<KingdomBriefModel> kindoms;

    public KingdomListSuccessEvent(List<KingdomBriefModel> kindoms) {
        this.kindoms = kindoms;
    }

    public List<KingdomBriefModel> getKindoms() {
        return kindoms;
    }
}
