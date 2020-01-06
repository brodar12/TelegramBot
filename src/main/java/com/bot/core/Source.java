package com.bot.core;


import com.bot.entity.GlobalResponse;
import com.bot.scenario.handler.Scenario1;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class Source {

    public static void main(String[] args) throws InterruptedException {

        BotCommandExecutor botexec = new BotCommandExecutor();
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Scenario1 scenario = (Scenario1) context.getBean("scenario1");

        final String botToken = "934437958:AAGaIZjKSzdSnMHDabQsw10wG5RI1CvmLPY";
        List<GlobalResponse> getAllUpdates;
        boolean access = true;
        int incre = 0;

        TelegramBot bot = new TelegramBot(botToken);


        while (access) {

            if (incre == 1000) access = false;

            try {
                //get all updates
                getAllUpdates = botexec.executeUpdate(bot);
                System.out.println();

                for (GlobalResponse resp : getAllUpdates) {
                    //scenario.process("",bot,resp);
                    try{
                    bot.execute(new SendPhoto(resp.getResponseMessage().getChatId(), resp.getPhotoSizes()[0].fileId()));
                    }catch (NullPointerException e){
                        System.out.println(e);
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


     /*   public static List<ResponseMessage> executeUpdate (TelegramBot bot) throws Exception {

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

*/
}

