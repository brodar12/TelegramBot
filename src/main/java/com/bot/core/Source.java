package com.bot.core;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;

import com.bot.entity.ResponseMessage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.*;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class Source {

    public static void main(String[] args) throws InterruptedException {

        final String botToken = "934437958:AAGaIZjKSzdSnMHDabQsw10wG5RI1CvmLPY";
        List<ResponseMessage> getAllUpdates = null;
        boolean access = true;
        int incre = 0;

        TelegramBot bot = new TelegramBot(botToken);


        while (access) {

            if (incre == 1000) access = false;

            try {
                //get all updates
                getAllUpdates = executeUpdate(bot);
                System.out.println();

                for (ResponseMessage resp : getAllUpdates) {

                    if (resp.getMessageText().toString().equals("/start")) {

                        displayKeyborad(bot, resp.getChatId(), "Hello welcome in test chat!!!!");
                        forceUpdate(bot, resp.getUpdateID());

                    } else if (resp.getMessageText().toString().equals("button1")) {

                    }

                }


            } catch (Exception e) {
                System.out.println(e);
                access = false;
            }

            ++incre;
            Thread.sleep(1500);
        }


    }


        public static List<ResponseMessage> executeUpdate (TelegramBot bot) throws Exception {

            List<ResponseMessage> allmessages = new ArrayList<ResponseMessage>();
            GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);

            GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
            List<Update> updates = updatesResponse.updates();

            for (Update up : updates) {
                System.out.println(up.message().messageId() + ":" + up.message().chat().id()
                        + ":" + up.message().text() + ":" + up.updateId());

                allmessages.add(new ResponseMessage(up.message().chat().id(), up.message().messageId(),
                        up.updateId(), up.message().text()));
            }

            return allmessages;
        }


        public static void displayKeyborad (TelegramBot bot,long chatId, String text){

            Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                    new String[]{"button1", "button2"},
                    new String[]{"button3", "button4"})
                    .oneTimeKeyboard(true)   // optional
                    .resizeKeyboard(true)    // optional
                    .selective(true);

            SendMessage request = new SendMessage(chatId, text)
                    .parseMode(ParseMode.HTML)
                    .disableWebPagePreview(true)
                    .disableNotification(true)
                    .replyToMessageId(1)
                    .replyMarkup(replyKeyboardMarkup);

            SendResponse sendResponse = bot.execute(request);
            boolean ok = sendResponse.isOk();
            Message message = sendResponse.message();

            System.out.println("Message status:" + ok);
            System.out.println("Message text:" + message);

        }


        public static boolean forceUpdate (TelegramBot bot,int updateId){

            GetUpdates getUpdates = new GetUpdates().limit(100).offset(updateId + 1).timeout(0);
            GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
            return updatesResponse.isOk();
        }


}

