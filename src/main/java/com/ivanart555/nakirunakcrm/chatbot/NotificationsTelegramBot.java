package com.ivanart555.nakirunakcrm.chatbot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Component
public class NotificationsTelegramBot extends DefaultAbsSender {


    protected NotificationsTelegramBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotToken() {
        return "5672401311:AAEPfMGruka2CHLQWA1CcHLM7tvr-6BN53A";
    }


}
