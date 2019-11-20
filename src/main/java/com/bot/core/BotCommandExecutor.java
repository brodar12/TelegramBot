package com.bot.core;

import com.bot.entity.GlobalResponse;
import com.bot.entity.ResponseCallbackQuery;
import com.bot.entity.ResponseMessage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import com.sun.corba.se.impl.protocol.giopmsgheaders.ReplyMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BotCommandExecutor {



    public  List<GlobalResponse> executeUpdate (TelegramBot bot) {

        List<GlobalResponse> allmessages = new ArrayList<GlobalResponse>();
        ResponseMessage responseMessage=null;
        ResponseCallbackQuery responseCallbackQuery=null;

        GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);

        GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
        List<Update> updates = updatesResponse.updates();

        try {
            for (Update up : updates) {
                System.out.println(up.toString());
//                System.out.println("Display update:"+up.message().messageId() + ":" + up.message().chat().id()
//                        + ":" + up.message().text() + ":" + up.updateId());

                if(up.message()!=null) {
                    responseMessage=new ResponseMessage(up.message().chat().id(), up.message().messageId(),
                            up.updateId(), up.message().text());
                }
                else if(up.callbackQuery()!=null){
                    responseCallbackQuery= new ResponseCallbackQuery(up.callbackQuery().chatInstance(), up.callbackQuery().from().id(),
                                                                up.updateId(), up.callbackQuery().data(),up.callbackQuery().message().messageId());
                }
                allmessages.add(new GlobalResponse(responseMessage,responseCallbackQuery));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return allmessages;
    }


    public void displayKeyborad (TelegramBot bot, long chatId, String text, Map<String,String> buttonDisplay){
        int incre=0;
        InlineKeyboardButton[][] inlineKeyboardButtons= new InlineKeyboardButton[buttonDisplay.size()/2][2];

        for(int i=0;i<buttonDisplay.size()/2;++i){
            for(int j=0;j<2;++j){
                String key=(String) buttonDisplay.keySet().toArray()[incre];
                String value=buttonDisplay.get(key);
                inlineKeyboardButtons[i][j]=new InlineKeyboardButton(key).callbackData(value);
                ++incre;
            }
        }

        InlineKeyboardMarkup arrayKey= new InlineKeyboardMarkup(inlineKeyboardButtons);

        SendMessage request = new SendMessage(chatId, text)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true)
                .replyToMessageId(1)
                .replyMarkup(arrayKey);

        SendResponse sendResponse = bot.execute(request);
        boolean ok = sendResponse.isOk();
        Message message = sendResponse.message();

        System.out.println("Message status:" + ok);
        System.out.println("Message text:" + message);

    }


    public  boolean forceUpdate (TelegramBot bot,int updateId){

        GetUpdates getUpdates = new GetUpdates().limit(100).offset(updateId + 1).timeout(0);
        GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
        return updatesResponse.isOk();
    }

    public boolean deleteMessage(TelegramBot bot, String chatId, int messageId){
        DeleteMessage deleteMessage= new DeleteMessage(chatId,messageId);
        return bot.execute(deleteMessage).isOk();
    }
}
