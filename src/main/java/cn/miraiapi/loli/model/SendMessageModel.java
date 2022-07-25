package com.example.mirarifox.model;

import com.example.mirarifox.Resp.MessageType;

import java.util.List;

public class SendMessageModel {
    String sessionKey,target;
    List<MessageType>messageChain;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<MessageType> getMessageChain() {
        return messageChain;
    }

    public void setMessageChain(List<MessageType> messageChain) {
        this.messageChain = messageChain;
    }

    public static class Message{
        String type="Plain";
        String text;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
