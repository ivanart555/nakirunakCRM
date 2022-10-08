package com.ivanart555.nakirunakcrm.services.impl;

import com.ivanart555.nakirunakcrm.entities.Customer;
import com.ivanart555.nakirunakcrm.entities.Order;
import com.ivanart555.nakirunakcrm.exception.ServiceException;
import com.ivanart555.nakirunakcrm.repository.CustomerRepository;
import com.ivanart555.nakirunakcrm.services.CustomerService;
import com.ivanart555.nakirunakcrm.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Component
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderService orderService;

    @Override
    public List<Customer> findAll() throws ServiceException {
        List<Customer> customers = customerRepository.findAll();
        log.info("All Customers received successfully.");
        return customers;
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) throws ServiceException {
        Page<Customer> customers = customerRepository.findAll(pageable);
        log.info("All Customers received successfully.");
        return customers;
    }

    @Override
    public Customer findByPhoneNumber(String phoneNumber) throws ServiceException {
        Customer customer = null;
        try {
            customer = customerRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new ServiceException(
                    String.format("Customer with phone number %s not found!", phoneNumber)));
        } catch (EntityNotFoundException e) {
            log.warn("Customer with phone number {} not found!", phoneNumber);
        }
        log.info("Customer with phone number {} received successfully.", phoneNumber);

        return customer;
    }

    @Override
    public void assignPublicId(Customer customer) throws ServiceException {
        Customer lastCustomer = customerRepository.findFirstByOrderByPublicIdDesc();

        if (lastCustomer != null) {
            customer.setPublicId(lastCustomer.getPublicId() + 1);
            log.info("The last customer found sorted by public id!");
        } else {
            customer.setPublicId(1);
            log.info("The last customer not found sorted by public id! Public id set to 1!");
        }
    }

    @Override
    public Customer findById(Integer id) throws ServiceException {
        Customer customer = null;
        try {
            customer = customerRepository.findById(id).orElseThrow(() -> new ServiceException(
                    String.format("Customer with id %d not found!", id)));
        } catch (EntityNotFoundException e) {
            log.warn("Customer with id {} not found!", id);
        }
        log.info("Customer with id {} received successfully.", id);

        return customer;
    }

    @Override
    public int save(Customer customer) {
        Customer createdCustomer = customerRepository.save(customer);
        log.info("Customer with id {} saved successfully.", createdCustomer.getId());
        return createdCustomer.getId();
    }

    @Override
    public void deleteById(Integer id) throws ServiceException {
        deleteCustomerOrders(id);

        customerRepository.deleteById(id);
        log.info("Customer with id {} deleted successfully.", id);
    }

    private void deleteCustomerOrders(Integer customerId) {
        Set<Order> orders = findById(customerId).getOrders();
        for (Order order : orders) {
            orderService.deleteById(order.getId());
        }
    }

}
