package com.ivanart555.nakirunakcrm.repository;

import com.ivanart555.nakirunakcrm.entities.Customer;
import com.ivanart555.nakirunakcrm.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}