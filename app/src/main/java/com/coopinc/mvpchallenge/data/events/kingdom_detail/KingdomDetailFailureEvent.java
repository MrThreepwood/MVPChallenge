package com.coopinc.mvpchallenge.data.events.kingdom_detail;

public class KingdomDetailFailureEvent {

    private String error;

    public KingdomDetailFailureEvent(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
