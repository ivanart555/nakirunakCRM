package com.ivanart555.nakirunakcrm.services;

import com.ivanart555.nakirunakcrm.entities.Customer;
import com.ivanart555.nakirunakcrm.exception.ServiceException;
import com.ivanart555.nakirunakcrm.services.generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService extends GenericService<Customer, Integer> {

    Page<Customer> findAll(Pageable pageable) throws ServiceException;

    Customer findByPhoneNumber(String phoneNumber) throws ServiceException;

}
