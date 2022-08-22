package com.ivanart555.nakirunakcrm.services;

import com.ivanart555.nakirunakcrm.entities.OrderStatus;
import com.ivanart555.nakirunakcrm.exception.ServiceException;
import com.ivanart555.nakirunakcrm.services.generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderStatusService extends GenericService<OrderStatus, Integer> {

    Page<OrderStatus> findAll(Pageable pageable) throws ServiceException;

    OrderStatus findByName(String name) throws ServiceException;

}
