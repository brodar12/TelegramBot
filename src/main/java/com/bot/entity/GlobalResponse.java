package com.bot.entity;

import com.pengrad.telegrambot.model.PhotoSize;

public class GlobalResponse {

    private ResponseMessage responseMessage;
    private ResponseCallbackQuery responseCallbackQuery;
    private PhotoSize[] photoSizes;
    public GlobalResponse() {

    }

    public GlobalResponse(ResponseMessage responseMessage, ResponseCallbackQuery responseCallbackQuery, PhotoSize[] photoSizes) {
        this.responseMessage = responseMessage;
        this.responseCallbackQuery = responseCallbackQuery;
        this.photoSizes = photoSizes;
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

    public PhotoSize[] getPhotoSizes() {
        return photoSizes;
    }

    public void setPhotoSizes(PhotoSize[] photoSizes) {
        this.photoSizes = photoSizes;
    }
}
