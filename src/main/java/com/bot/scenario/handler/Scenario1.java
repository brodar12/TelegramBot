package com.bot.scenario.handler;

import com.bot.core.BotCommandExecutor;
import com.bot.entity.ResponseMessage;
import com.pengrad.telegrambot.TelegramBot;

import java.util.ArrayList;
import java.util.List;

public class Scenario1 extends BotCommandExecutor implements Step {

    private Step nextStep;
    private List<String> allComand= new ArrayList<>();
    private String messageAction;


    @Override
    public void process(String message, TelegramBot bot, ResponseMessage resp) {

        System.out.println("S1");

            if(messageAction.equals(resp.getMessageText())){

                System.out.println("S1 Execute command:"+resp.getMessageText());
                super.displayKeyborad(bot,resp.getChatId(),"Step1",allComand);
                super.forceUpdate(bot,resp.getUpdateID());
             }
             else{ nextStep.process(message,bot,resp); }

    }

    public List<String> getAllComand() {
        return allComand;
    }

    public void setAllComand(List<String> allComand) {
        this.allComand = allComand;
    }

    public Step getNextStep() {
        return nextStep;
    }

    public void setNextStep(Step nextStep) {
        this.nextStep = nextStep;
    }

    public String getMessageAction() {return messageAction; }

    public void setMessageAction(String messageAction) { this.messageAction = messageAction; }
}
