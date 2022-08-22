package com.ivanart555.nakirunakcrm.api.rest_controller;

import com.ivanart555.nakirunakcrm.entities.Destination;
import com.ivanart555.nakirunakcrm.services.DestinationService;
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
@RequestMapping("/api/destinations")
public class DestinationRestController {
    private final DestinationService destinationService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Destination> findAll() {
        return destinationService.findAll();
    }

    @GetMapping("/{id}")
    public Destination findById(@PathVariable("id") int id) {
        return destinationService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody Destination destination) {
        int id = destinationService.save(destination);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Destination update(@RequestBody Destination destination) {
        destinationService.save(destination);
        return destination;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        destinationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
