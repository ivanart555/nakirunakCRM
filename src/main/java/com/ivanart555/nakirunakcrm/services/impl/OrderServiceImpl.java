package com.ivanart555.nakirunakcrm.services.impl;

import com.ivanart555.nakirunakcrm.entities.Order;
import com.ivanart555.nakirunakcrm.exception.ServiceException;
import com.ivanart555.nakirunakcrm.repository.OrderRepository;
import com.ivanart555.nakirunakcrm.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> findAll() throws ServiceException {
        List<Order> orders = orderRepository.findAll();
        log.info("All Orders received successfully.");
        return orders;
    }

    @Override
    public Page<Order> findAll(Pageable pageable) throws ServiceException {
        Page<Order> orders = orderRepository.findAll(pageable);
        log.info("All Orders received successfully.");
        return orders;
    }

    @Override
    public Order findById(Integer id) throws ServiceException {
        Order order = null;
        try {
            order = orderRepository.findById(id).orElseThrow(() -> new ServiceException(
                    String.format("Order with id %d not found!", id)));
        } catch (EntityNotFoundException e) {
            log.warn("Order with id {} not found!", id);
        }
        log.info("Order with id {} received successfully.", id);

        return order;
    }

    @Override
    public int save(Order order) {
        Order createdOrder = orderRepository.save(order);
        log.info("Order with id {} saved successfully.", createdOrder.getId());
        return createdOrder.getId();
    }

    @Override
    public void deleteById(Integer id) throws ServiceException {
        orderRepository.deleteById(id);
        log.info("Order with id {} deleted successfully.", id);
    }
}
