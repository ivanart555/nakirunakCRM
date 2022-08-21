package com.ivanart555.nakirunakcrm.repository;

import com.ivanart555.nakirunakcrm.entities.Order;
import com.ivanart555.nakirunakcrm.entities.OrderStatus;
import com.ivanart555.nakirunakcrm.services.OrderStatusService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

        Optional<OrderStatus> findByName(String name);

}
