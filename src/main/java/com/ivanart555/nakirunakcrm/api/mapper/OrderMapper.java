package com.ivanart555.nakirunakcrm.api.mapper;

import com.ivanart555.nakirunakcrm.entities.Order;
import com.ivanart555.nakirunakcrm.entities.dto.OrderDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class OrderMapper {
    private final ModelMapper modelMapper;

    public Order convertToEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
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
