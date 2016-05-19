package com.coopinc.mvpchallenge.data.events.kingdom_detail;

import com.coopinc.mvpchallenge.data.models.KingdomModel;

public class KingdomDetailSuccessEvent {
    private KingdomModel kingdom;

    public KingdomDetailSuccessEvent(KingdomModel kingdom) {

        this.kingdom = kingdom;
    }

    public KingdomModel getKingdom() {
        return kingdom;
    }
}
