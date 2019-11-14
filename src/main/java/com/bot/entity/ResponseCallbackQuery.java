package com.bot.entity;

public class ResponseCallbackQuery {

    private String chat_instance;
    private Integer chatID;
    private Integer updateID;
    private String data;

    public ResponseCallbackQuery() {

    }


    public ResponseCallbackQuery(String chat_instance, Integer chatID, Integer updateID, String data) {
        this.chat_instance = chat_instance;
        this.chatID = chatID;
        this.updateID = updateID;
        this.data = data;
    }

    public String getChat_instance() {
        return chat_instance;
    }

    public void setChat_instance(String chat_instance) {
        this.chat_instance = chat_instance;
    }

    public Integer getChatID() {
        return chatID;
    }

    public void setChatID(Integer chatID) {
        this.chatID = chatID;
    }

    public int getUpdateID() {
        return updateID;
    }

    public void setUpdateID(int updateID) {
        this.updateID = updateID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
