package com.bot.core;

import com.bot.entity.ResponseMessage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.ArrayList;
import java.util.List;

public class BotCommandExecutor {



    public  List<ResponseMessage> executeUpdate (TelegramBot bot) throws Exception {

        List<ResponseMessage> allmessages = new ArrayList<ResponseMessage>();
        GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);

        GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
        List<Update> updates = updatesResponse.updates();

        for (Update up : updates) {
            System.out.println(up.toString());
//            System.out.println(up.message().messageId() + ":" + up.message().chat().id()
//                    + ":" + up.message().text() + ":" + up.updateId());

            if(up.message().text()!=null) {
                allmessages.add(new ResponseMessage(up.message().chat().id(), up.message().messageId(),
                        up.updateId(), up.message().text()));
            }
        }

        return allmessages;
    }


    public void displayKeyborad (TelegramBot bot,long chatId, String text,List<String> buttonDisplay){

        String[][] buttonsArray= new String[buttonDisplay.size()/2][2];

        int incre=0;
        for(int i=0;i<buttonDisplay.size()/2;++i){
            for(int j=0;j<2;++j){
                buttonsArray[i][j]=buttonDisplay.get(incre);
                ++incre;
            }
        }

        String[] buttons= new String[buttonDisplay.size()];


        InlineKeyboardButton[][] inlineKeyboardButtons= new InlineKeyboardButton[][]{
                {new InlineKeyboardButton("First1").callbackData("command1"),new InlineKeyboardButton("First2").callbackData("command2")},
                {new InlineKeyboardButton("First3").callbackData("command3"),new InlineKeyboardButton("First4").callbackData("command4")}
        };

        InlineKeyboardMarkup arrayKey= new InlineKeyboardMarkup(inlineKeyboardButtons);

        /*Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                buttonsArray)
                .oneTimeKeyboard(true)   // optional
                .resizeKeyboard(true)    // optional
                .selective(true);
         */



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
}
