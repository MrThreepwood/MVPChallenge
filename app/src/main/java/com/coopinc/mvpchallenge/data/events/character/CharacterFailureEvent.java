package com.coopinc.mvpchallenge.data.events.character;

public class CharacterFailureEvent {
    private String error;

    public CharacterFailureEvent(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
