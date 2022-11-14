package com.ivanart555.nakirunakcrm.chatbot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Component
public class NotificationsTelegramBot extends DefaultAbsSender {

    @Value("${telegram.bot.token}")
    private String telegramBotToken;

    protected NotificationsTelegramBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotToken() {
        return telegramBotToken;
    }


}
