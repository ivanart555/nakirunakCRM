package com.ivanart555.nakirunakcrm.api.rest_controller;

import com.ivanart555.nakirunakcrm.entities.Customer;
import com.ivanart555.nakirunakcrm.entities.dto.CustomerDto;
import com.ivanart555.nakirunakcrm.services.CustomerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable("id") int id) {
        return customerService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody CustomerDto customerDto) {
        Customer customer = convertToEntity(customerDto);

        int id = customerService.save(customer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    private Customer convertToEntity(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Customer update(@RequestBody Customer customer) {
        customerService.save(customer);
        return customer;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
