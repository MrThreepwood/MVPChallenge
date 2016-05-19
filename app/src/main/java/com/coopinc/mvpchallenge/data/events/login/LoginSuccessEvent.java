package com.coopinc.mvpchallenge.data.events.login;

import com.coopinc.mvpchallenge.data.models.MessageModel;

public class LoginSuccessEvent {
    private final MessageModel messageModel;

    public LoginSuccessEvent(MessageModel messageModel) {
        this.messageModel = messageModel;
    }

    public MessageModel getMessageModel() {
        return messageModel;
    }
}
