package com.bot.core;

import com.bot.entity.GlobalResponse;
import com.bot.entity.ResponseCallbackQuery;
import com.bot.entity.ResponseMessage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BotCommandExecutor {


    public List<GlobalResponse> executeUpdate(TelegramBot bot) {

        List<GlobalResponse> allmessages = new ArrayList<GlobalResponse>();
        ResponseMessage responseMessage = new ResponseMessage();
        ResponseCallbackQuery responseCallbackQuery = new ResponseCallbackQuery();
        PhotoSize[] photoSizes=new PhotoSize[100];

        GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);

        GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
        List<Update> updates = updatesResponse.updates();
        try {
            for (Update up : updates) {
                System.out.println(up.toString());
//                System.out.println("Display update:"+up.message().messageId() + ":" + up.message().chat().id()
//                        + ":" + up.message().text() + ":" + up.updateId());

                if (up.message() != null) {
                    responseMessage = new ResponseMessage(up.message().chat().id(), up.message().messageId(),
                            up.updateId(), up.message().text());
                }
                if (up.callbackQuery() != null) {
                    responseCallbackQuery = new ResponseCallbackQuery(up.callbackQuery().chatInstance(), up.callbackQuery().from().id(),
                            up.updateId(), up.callbackQuery().data(), up.callbackQuery().message().messageId());
                }
                 if (up.message().photo().length >= 0) {
                    photoSizes= up.message().photo();
                }
                allmessages.add(new GlobalResponse(responseMessage, responseCallbackQuery,photoSizes));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return allmessages;
    }


    public void displayKeyborad(TelegramBot bot, long chatId, String text, Map<String, String> buttonDisplay) {
        int incre = 0;
        InlineKeyboardButton[][] inlineKeyboardButtons = new InlineKeyboardButton[buttonDisplay.size() / 2][2];

        for (int i = 0; i < buttonDisplay.size() / 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                String key = (String) buttonDisplay.keySet().toArray()[incre];
                String value = buttonDisplay.get(key);
                inlineKeyboardButtons[i][j] = new InlineKeyboardButton(key).callbackData(value);
                ++incre;
            }
        }

        InlineKeyboardMarkup arrayKey = new InlineKeyboardMarkup(inlineKeyboardButtons);

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


    public boolean forceUpdate(TelegramBot bot, int updateId) {

        GetUpdates getUpdates = new GetUpdates().limit(100).offset(updateId + 1).timeout(0);
        GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
        return updatesResponse.isOk();
    }

    public boolean deleteMessage(TelegramBot bot, String chatId, int messageId) {
        DeleteMessage deleteMessage = new DeleteMessage(chatId, messageId);
        return bot.execute(deleteMessage).isOk();
    }
}
