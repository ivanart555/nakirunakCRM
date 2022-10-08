package com.ivanart555.nakirunakcrm.chatbot;

import com.ivanart555.nakirunakcrm.entities.Order;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Component
public class MessageGenerator {
    private final Environment env;

    public SendMessage generateNotificationsMessage(Order order) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return SendMessage.builder().chatId(Objects.requireNonNull(env.getProperty("telegram.bot.chatid")))
                .text("Новая Замова №" + order.getPublicId() + " ад " +
                        order.getTimestamp().format(formatter) + System.lineSeparator() + order.getCustomer().getName() +
                        System.lineSeparator() + order.getCustomer().getPhoneNumber() + System.lineSeparator() +
                        order.getDestination().getName() + System.lineSeparator() + Optional.ofNullable(order.getCustomerComment()).orElse("")).build();

    }


}
