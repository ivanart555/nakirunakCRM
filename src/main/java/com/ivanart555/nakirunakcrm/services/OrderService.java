package com.ivanart555.nakirunakcrm.services;

import com.ivanart555.nakirunakcrm.entities.Order;
import com.ivanart555.nakirunakcrm.exception.ServiceException;
import com.ivanart555.nakirunakcrm.services.generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService extends GenericService<Order, Integer> {

    Page<Order> findAll(Pageable pageable) throws ServiceException;

}
