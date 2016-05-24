package com.coopinc.mvpchallenge.data.events.character;

public class CharacterFailureEvent {
    private String error;
    private String attemptedUrl;

    public CharacterFailureEvent(String error, String attemptedUrl) {
        this.error = error;
        this.attemptedUrl = attemptedUrl;
    }

    public String getError() {
        return error;
    }

    public String getAttemptedUrl() {
        return attemptedUrl;
    }
}
