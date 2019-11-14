package com.bot.scenario.handler;

import com.bot.core.BotCommandExecutor;
import com.bot.entity.GlobalResponse;
import com.pengrad.telegrambot.TelegramBot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scenario1 extends BotCommandExecutor implements Step {

    private Step nextStep;
    private Map<String,String> allComand= new HashMap<>();
    private String messageAction;


    @Override
    public void process(String message, TelegramBot bot, GlobalResponse resp) {

        System.out.println("S1");

          if(resp.getResponseMessage()!=null){

                  if(messageAction.equals(resp.getResponseMessage().getMessageText())){

                      System.out.println("S1 Execute command:"+resp.getResponseMessage().getMessageText());
                      super.displayKeyborad(bot,resp.getResponseMessage().getChatId(),"Selectati sistemul de operare!!!",allComand);
                      super.forceUpdate(bot,resp.getResponseMessage().getUpdateID());
                  }
                  else{ nextStep.process(message,bot,resp); }
          }
          else{ nextStep.process(message,bot,resp); }

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
