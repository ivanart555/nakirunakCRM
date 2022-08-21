package com.ivanart555.nakirunakcrm.api.rest_controller;

import com.ivanart555.nakirunakcrm.entities.Order;
import com.ivanart555.nakirunakcrm.entities.OrderStatus;
import com.ivanart555.nakirunakcrm.entities.dto.OrderDto;
import com.ivanart555.nakirunakcrm.services.DestinationService;
import com.ivanart555.nakirunakcrm.services.OrderService;
import com.ivanart555.nakirunakcrm.services.OrderStatusService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrdersRestController {
    private final OrderService orderService;
    private final OrderStatusService orderStatusService;
    private final DestinationService destinationService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable("id") int id) {
        return orderService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody OrderDto orderDto) {
        Order order = convertToEntity(orderDto);

        order.setTimestamp(LocalDateTime.now());
        order.setOrderStatus(orderStatusService.findByName("Новая"));
        order.setDestination(destinationService.findByName(orderDto.getDestination()));
        order.setComment("");

        int id = orderService.save(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    private Order convertToEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
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
