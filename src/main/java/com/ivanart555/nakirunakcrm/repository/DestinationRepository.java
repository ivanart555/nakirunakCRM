package com.ivanart555.nakirunakcrm.repository;

import com.ivanart555.nakirunakcrm.entities.Destination;
import com.ivanart555.nakirunakcrm.entities.Order;
import com.ivanart555.nakirunakcrm.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface DestinationRepository extends JpaRepository<Destination, Integer> {

    Optional<Destination> findByName(String name);

}
