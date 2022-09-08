package com.ivanart555.nakirunakcrm.api.controller;

import com.ivanart555.nakirunakcrm.entities.Destination;
import com.ivanart555.nakirunakcrm.exception.ServiceException;
import com.ivanart555.nakirunakcrm.services.DestinationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Controller
@RequestMapping("/destinations")
public class DestinationController {
    private static final String REDIRECT_DESTINATIONS = "redirect:/destinations";
    private final DestinationService destinationService;

    @GetMapping()
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) throws ServiceException {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(15);

        Pageable sortedById = PageRequest.of(currentPage - 1, pageSize, Sort.by("id"));
        Page<Destination> destinationPage = destinationService.findAll(sortedById);

        model.addAttribute("destinationPage", destinationPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", destinationPage.getTotalPages());

        model.addAttribute("destination", new Destination());

        int totalPages = destinationPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "destinations/index";
    }

    @PostMapping()
    public String create(@ModelAttribute("destination") Destination destination)
            throws ServiceException {
        destinationService.assignPublicId(destination);

        destinationService.save(destination);
        return REDIRECT_DESTINATIONS;
    }

    @PatchMapping("/edit")
    public String update(@ModelAttribute("destination") Destination destination)
            throws ServiceException {

        destinationService.save(destination);
        return REDIRECT_DESTINATIONS;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) throws ServiceException {
        destinationService.deleteById(id);
        return REDIRECT_DESTINATIONS;
    }
}
