package com.bot.scenario.handler;

import java.util.ArrayList;
import java.util.List;

public class Scenario1 extends Object implements Step {

    private Step nextStep;
    private List<String> allComand= new ArrayList<>();

    @Override
    public void setNextScenario(Step step) {
          this.nextStep=step;
    }

    @Override
    public void process(String message) {
        boolean next_step=true;

        System.out.println("S1");

        for(String command:allComand){
            if(command.equals(message)){
                System.out.println("S1 Execute command:"+message);
                next_step=false;
            }
        }
        if(next_step)nextStep.process(message);
    }

    @Override
    public void setAllCommand(String message) {
        this.allComand.add(message);
    }


}
