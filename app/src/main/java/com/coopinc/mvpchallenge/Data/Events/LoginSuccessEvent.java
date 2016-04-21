package com.coopinc.mvpchallenge.Data.Events;

import com.coopinc.mvpchallenge.Data.MessageModel;

public class LoginSuccessEvent {
    private final MessageModel messageModel;

    public LoginSuccessEvent(MessageModel messageModel) {
        this.messageModel = messageModel;
    }

    public MessageModel getMessageModel() {
        return messageModel;
    }
}
