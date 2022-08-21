package com.ivanart555.nakirunakcrm.services;

import com.ivanart555.nakirunakcrm.entities.Customer;
import com.ivanart555.nakirunakcrm.entities.Destination;
import com.ivanart555.nakirunakcrm.entities.OrderStatus;
import com.ivanart555.nakirunakcrm.exception.ServiceException;
import com.ivanart555.nakirunakcrm.services.generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DestinationService extends GenericService<Destination, Integer> {

    Page<Destination> findAll(Pageable pageable) throws ServiceException;

    Destination findByName(String name) throws ServiceException;

}
