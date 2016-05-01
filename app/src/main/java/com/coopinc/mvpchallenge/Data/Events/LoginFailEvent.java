package com.coopinc.mvpchallenge.data.events;

public class LoginFailEvent {
    private String error = "An unexpected error occured";

    public LoginFailEvent(String error) {
        if (error != null && !error.isEmpty()) {
            this.error = error;
        }
    }

    public LoginFailEvent() {

    }

    public String getError() {
        return error;
    }
}
