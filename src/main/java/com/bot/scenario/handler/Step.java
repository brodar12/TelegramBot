package com.bot.scenario.handler;

public interface Step {
     void setNextScenario(Step step);
     void process(String message);
     void setAllCommand(String message);

}
