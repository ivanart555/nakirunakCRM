package com.ivanart555.nakirunakcrm.api.rest_controller;

import com.ivanart555.nakirunakcrm.entities.Order;
import com.ivanart555.nakirunakcrm.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrdersRestController {
    private final OrderService orderService;

    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable("id") int id) {
        return orderService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestParam Map<String, String> body) throws Exception {
        String orderName = body.get("name");
        URI uri = null;
        if (orderName != null){
            Order order = new Order();
            int id = orderService.save(order);
            uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            throw new Exception();
         }

        return ResponseEntity.created(uri).build();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Order update(@RequestBody Order order) {
        orderService.save(order);
        return order;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
