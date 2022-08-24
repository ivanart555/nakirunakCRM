package com.ivanart555.nakirunakcrm.api.rest_controller;

import com.ivanart555.nakirunakcrm.entities.OrderStatus;
import com.ivanart555.nakirunakcrm.services.OrderStatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/statuses")
public class OrderStatusRestController {
    private final OrderStatusService orderStatusService;

    @GetMapping
    public List<OrderStatus> findAll() {
        return orderStatusService.findAll();
    }

    @GetMapping("/{id}")
    public OrderStatus findById(@PathVariable("id") int id) {
        return orderStatusService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody OrderStatus orderStatus) {
        int id = orderStatusService.save(orderStatus);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public OrderStatus update(@RequestBody OrderStatus orderStatus) {
        orderStatusService.save(orderStatus);
        return orderStatus;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        orderStatusService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
