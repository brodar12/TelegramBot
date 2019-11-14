package com.bot.entity;

public class GlobalResponse {

    private ResponseMessage responseMessage;
    private ResponseCallbackQuery responseCallbackQuery;

    public GlobalResponse() {

    }

    public GlobalResponse(ResponseMessage responseMessage, ResponseCallbackQuery responseCallbackQuery) {
        this.responseMessage = responseMessage;
        this.responseCallbackQuery = responseCallbackQuery;
    }


    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ResponseCallbackQuery getResponseCallbackQuery() {
        return responseCallbackQuery;
    }

    public void setResponseCallbackQuery(ResponseCallbackQuery responseCallbackQuery) {
        this.responseCallbackQuery = responseCallbackQuery;
    }

}
