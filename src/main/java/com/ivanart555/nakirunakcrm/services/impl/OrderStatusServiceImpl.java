package com.ivanart555.nakirunakcrm.services.impl;

import com.ivanart555.nakirunakcrm.entities.OrderStatus;
import com.ivanart555.nakirunakcrm.exception.ServiceException;
import com.ivanart555.nakirunakcrm.repository.OrderStatusRepository;
import com.ivanart555.nakirunakcrm.services.OrderStatusService;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    @Override
    public List<OrderStatus> findAll() throws ServiceException {
        List<OrderStatus> statuses = orderStatusRepository.findAll();
        log.info("All orders statuses received successfully.");
        return statuses;
    }

    @Override
    public Page<OrderStatus> findAll(Pageable pageable) throws ServiceException {
        Page<OrderStatus> statuses = orderStatusRepository.findAll(pageable);
        log.info("All orders statuses received successfully.");
        return statuses;
    }

    @Override
    public OrderStatus findById(Integer id) throws ServiceException {
        OrderStatus status = null;
        try {
            status = orderStatusRepository.findById(id).orElseThrow(() -> new ServiceException(
                    String.format("Order status with id %d not found!", id)));
        } catch (EntityNotFoundException e) {
            log.warn("Order status with id {} not found!", id);
        }
        log.info("Order status with id {} received successfully.", id);

        return status;
    }

    @Override
    public OrderStatus findByName(String name) throws ServiceException {
        OrderStatus status = null;
        try {
            status = orderStatusRepository.findByName(name).orElseThrow(() -> new ServiceException(
                    String.format("Order status with name %s not found!", name)));
        } catch (EntityNotFoundException e) {
            log.warn("Order status with name {} not found!", name);
        }
        log.info("Order status with name {} received successfully.", name);

        return status;
    }

    @Override
    public int save(OrderStatus status) {
        OrderStatus createdStatus = orderStatusRepository.save(status);
        log.info("Order status with id {} saved successfully.", createdStatus.getId());
        return createdStatus.getId();
    }

    @Override
    public void deleteById(Integer id) throws ServiceException {
        orderStatusRepository.deleteById(id);
        log.info("Order status with id {} deleted successfully.", id);
    }
}
