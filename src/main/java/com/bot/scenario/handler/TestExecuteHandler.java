package com.bot.scenario.handler;

public class TestExecuteHandler {

    public static void main(String[] args){
        Step scenario1= new Scenario1();
        Step scenario2= new Scenario2();

        scenario1.setAllCommand("Hack Android");
        scenario1.setAllCommand("Hack IOS");

        scenario2.setAllCommand("Samsung Galaxy S10 Plus");
        scenario2.setAllCommand("Google Pixel 3a");
        scenario2.setAllCommand("Samsung Galaxy Note 10 Plus");
        scenario2.setAllCommand("Google Pixel 4 XL");
        scenario2.setAllCommand("Samsung Galaxy S10e");

        scenario1.setNextScenario(scenario2);

        scenario1.process("Samsung Galaxy Note 10 Plus");

    }
}