package com.ivanart555.nakirunakcrm.services.impl;

import com.ivanart555.nakirunakcrm.entities.Destination;
import com.ivanart555.nakirunakcrm.exception.ServiceException;
import com.ivanart555.nakirunakcrm.repository.DestinationRepository;
import com.ivanart555.nakirunakcrm.services.DestinationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class DestinationServiceImpl implements DestinationService {
    private final DestinationRepository destinationRepository;

    @Override
    public List<Destination> findAll() throws ServiceException {
        List<Destination> destinations = destinationRepository.findAll();
        log.info("All Destinations received successfully.");
        return destinations;
    }

    @Override
    public Page<Destination> findAll(Pageable pageable) throws ServiceException {
        Page<Destination> destinations = destinationRepository.findAll(pageable);
        log.info("All Destinations received successfully.");
        return destinations;
    }

    @Override
    public Destination findById(Integer id) throws ServiceException {
        Destination destination = null;
        try {
            destination = destinationRepository.findById(id).orElseThrow(() -> new ServiceException(
                    String.format("Destination with id %d not found!", id)));
        } catch (EntityNotFoundException e) {
            log.warn("Destination with id {} not found!", id);
        }
        log.info("Destination with id {} received successfully.", id);

        return destination;
    }

    @Override
    public Destination findByName(String name) throws ServiceException {
        Destination destination = null;
        try {
            destination = destinationRepository.findByName(name).orElseThrow(() -> new ServiceException(
                    String.format("Destination with id %s not found!", name)));
        } catch (EntityNotFoundException e) {
            log.warn("Destination with id {} not found!", name);
        }
        log.info("Destination with id {} received successfully.", name);

        return destination;
    }

    @Override
    public void assignPublicId(Destination destination) throws ServiceException {
        Destination lastDestination = destinationRepository.findFirstByOrderByPublicIdDesc();

        if (lastDestination != null) {
            destination.setPublicId(lastDestination.getPublicId() + 1);
            log.info("The last destination found sorted by public id!");
        } else {
            destination.setPublicId(1);
            log.info("The last destination not found sorted by public id! Public id set to 1!");
        }
    }

    @Override
    public int save(Destination destination) {
        Destination createdDestination = destinationRepository.save(destination);
        log.info("Destination with id {} saved successfully.", createdDestination.getId());
        return createdDestination.getId();
    }

    @Override
    public void deleteById(Integer id) throws ServiceException {
        destinationRepository.deleteById(id);
        log.info("Destination with id {} deleted successfully.", id);
    }
}
