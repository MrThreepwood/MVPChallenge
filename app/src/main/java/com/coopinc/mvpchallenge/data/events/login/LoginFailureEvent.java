package com.coopinc.mvpchallenge.data.events.login;

public class LoginFailureEvent {
    private String error = "An unexpected error occured";

    public LoginFailureEvent(String error) {
        if (error != null && !error.isEmpty()) {
            this.error = error;
        }
    }

    public LoginFailureEvent() {

    }

    public String getError() {
        return error;
    }
}
