package com.ivanart555.nakirunakcrm.api.rest_controller;

import com.ivanart555.nakirunakcrm.api.mapper.OrderMapper;
import com.ivanart555.nakirunakcrm.chatbot.MessageGenerator;
import com.ivanart555.nakirunakcrm.chatbot.NotificationsTelegramBot;
import com.ivanart555.nakirunakcrm.entities.Order;
import com.ivanart555.nakirunakcrm.entities.dto.OrderDto;
import com.ivanart555.nakirunakcrm.services.DestinationService;
import com.ivanart555.nakirunakcrm.services.OrderService;
import com.ivanart555.nakirunakcrm.services.OrderStatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.URI;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
    private final OrderService orderService;
    private final OrderStatusService orderStatusService;
    private final DestinationService destinationService;
    private final OrderMapper orderMapper;
    private final NotificationsTelegramBot telegramBot;
    private final MessageGenerator messageGenerator;

    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable("id") int id) {
        return orderService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody OrderDto orderDto) throws TelegramApiException {

        if (orderDto.getCustomerName() == null) {  //tilda connection property save
            return ResponseEntity.ok().build();
        }

        Order order = orderMapper.convertToEntity(orderDto);
        orderService.assignPublicId(order);

        order.setTimestamp(ZonedDateTime.now(ZoneId.of("Europe/Minsk")).toLocalDateTime().truncatedTo(ChronoUnit.SECONDS));
        order.setOrderStatus(orderStatusService.findByName("Новая"));
        order.setDestination(destinationService.findByName(orderDto.getDestinationName()));

        int id = orderService.save(order);

        telegramBot.execute(messageGenerator.generateNotificationsMessage(order));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Order update(@RequestBody Order order) {
        orderService.save(order);
        return order;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
