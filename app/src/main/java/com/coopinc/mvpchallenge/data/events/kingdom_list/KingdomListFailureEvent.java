package com.coopinc.mvpchallenge.data.events.kingdom_list;


public class KingdomListFailureEvent {
    private String error;

    public KingdomListFailureEvent(String error) {
        this.error = error;

    }

    public String getError() {
        return error;
    }
}
