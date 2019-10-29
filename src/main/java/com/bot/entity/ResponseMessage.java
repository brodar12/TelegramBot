package com.bot.entity;

public class ResponseMessage {
    private long chatId;
    private int messageId;
    private int updateID;;
    private String messageText;


    public ResponseMessage() {

    }

    public ResponseMessage(long chatId, int messageId, int updateID, String messageText) {
        this.chatId = chatId;
        this.messageId = messageId;
        this.updateID = updateID;
        this.messageText = messageText;
    }


    public long getChatId() { return chatId; }

    public void setChatId(long chatId) { this.chatId = chatId; }

    public int getMessageId() { return messageId; }

    public void setMessageId(int messageId) { this.messageId = messageId; }

    public int getUpdateID() { return updateID; }

    public void setUpdateID(int updateID) { this.updateID = updateID; }

    public String getMessageText() { return messageText; }

    public void setMessageText(String messageText) { this.messageText = messageText; }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (chatId ^ (chatId >>> 32));
        result = prime * result + messageId;
        result = prime * result + ((messageText == null) ? 0 : messageText.hashCode());
        result = prime * result + updateID;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ResponseMessage other = (ResponseMessage) obj;
        if (chatId != other.chatId)
            return false;
        if (messageId != other.messageId)
            return false;
        if (messageText == null) {
            if (other.messageText != null)
                return false;
        } else if (!messageText.equals(other.messageText))
            return false;
        if (updateID != other.updateID)
            return false;
        return true;
    }


}
