package com.stardew.Messengers;

import java.util.ArrayList;

public class MessageBox {
    private ArrayList<String> messages;
    private String sender;
    private String receiver;

    public MessageBox(ArrayList<String> messages, String sender, String receiver) {
        this.messages = messages;
        this.sender = sender;
        this.receiver = receiver;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }
}
