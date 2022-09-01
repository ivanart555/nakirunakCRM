package com.ivanart555.nakirunakcrm.api.mapper;

import com.ivanart555.nakirunakcrm.entities.Customer;
import com.ivanart555.nakirunakcrm.entities.Destination;
import com.ivanart555.nakirunakcrm.entities.Order;
import com.ivanart555.nakirunakcrm.entities.OrderStatus;
import com.ivanart555.nakirunakcrm.entities.dto.OrderDto;
import com.ivanart555.nakirunakcrm.exception.ServiceException;
import com.ivanart555.nakirunakcrm.services.CustomerService;
import com.ivanart555.nakirunakcrm.services.DestinationService;
import com.ivanart555.nakirunakcrm.services.OrderStatusService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class OrderMapper {
    private final ModelMapper modelMapper;
    private final DestinationService destinationService;
    private final CustomerService customerService;
    private final OrderStatusService orderStatusService;

    public Order convertToEntity(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        order.setId(orderDto.getId());

        OrderStatus status;
        try {
            status = orderDto.getOrderStatusId() == null ? null : orderStatusService.findById(orderDto.getOrderStatusId());
            order.setOrderStatus(status);
        } catch (EntityNotFoundException e) {
            log.warn("Order status with id {} not found!", orderDto.getOrderStatusId());
        }

        Destination destination;
        try {
            destination = orderDto.getDestinationId() == null ? null : destinationService.findById(orderDto.getDestinationId());
            order.setDestination(destination);
        } catch (ServiceException e) {
            log.warn("Destination with id {} not found!", orderDto.getDestinationId());
        }

        Customer customer;
        try {
            customer = orderDto.getCustomerId() == null ? null : customerService.findById(orderDto.getCustomerId());
            order.setCustomer(customer);
        } catch (ServiceException e) {
            log.warn("Customer with id {} not found!", orderDto.getDestinationId());
        }

        return order;
    }

    public OrderDto convertToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    public List<OrderDto> convertEntitiesToDto(List<Order> orders) {
        List<OrderDto> ordersDto = new ArrayList<>();
        for (Order order : orders) {
            ordersDto.add(convertToDto(order));
        }
        return ordersDto;
    }

}
