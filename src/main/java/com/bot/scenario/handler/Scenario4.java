package com.bot.scenario.handler;

import com.bot.core.BotCommandExecutor;
import com.bot.entity.GlobalResponse;
import com.pengrad.telegrambot.TelegramBot;

import java.util.HashMap;
import java.util.Map;

public class Scenario4 extends BotCommandExecutor implements Step {

    private Step nextStep;
    private Map<String,String> allComand= new HashMap<>();
    private String messageAction;


    @Override
    public void process(String message, TelegramBot bot, GlobalResponse resp) {
        System.out.println("S4");

        if(resp.getResponseCallbackQuery()!=null) {

            if (messageAction.equals(resp.getResponseCallbackQuery().getData())) {

                System.out.println("chatId:"+resp.getResponseCallbackQuery().getChatID()+" MessageId:"+resp.getResponseCallbackQuery().getMessageId());
                super.deleteMessage(bot,resp.getResponseCallbackQuery().getChatID().toString(),resp.getResponseCallbackQuery().getMessageId());

                System.out.println("S4 Execute command:" + resp.getResponseCallbackQuery().getData());
                super.displayKeyborad(bot, resp.getResponseCallbackQuery().getChatID(), "Selectati daca victima are reteli de socilizare!!!", allComand);
                super.forceUpdate(bot, resp.getResponseCallbackQuery().getUpdateID());


            } else {
                if (nextStep != null) nextStep.process(message, bot, resp);
            }
        }
        else { if (nextStep != null) nextStep.process(message, bot, resp); }
    }


    public Step getNextStep() {
        return nextStep;
    }

    public void setNextStep(Step nextStep) {
        this.nextStep = nextStep;
    }

    public Map<String, String> getAllComand() {
        return allComand;
    }

    public void setAllComand(Map<String, String> allComand) {
        this.allComand = allComand;
    }

    public String getMessageAction() {
        return messageAction;
    }

    public void setMessageAction(String messageAction) {
        this.messageAction = messageAction;
    }
}
