package com.bot.scenario.handler;

import com.bot.entity.GlobalResponse;
import com.bot.entity.ResponseMessage;
import com.pengrad.telegrambot.TelegramBot;

public interface Step {
     void process(String message, TelegramBot bot, GlobalResponse resp);
}
