package com.ivanart555.nakirunakcrm.api.controller;

import com.ivanart555.nakirunakcrm.entities.OrderStatus;
import com.ivanart555.nakirunakcrm.exception.ServiceException;
import com.ivanart555.nakirunakcrm.services.OrderStatusService;
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
@RequestMapping("/statuses")
public class OrderStatusController {
    private static final String REDIRECT_STATUSES = "redirect:/statuses";
    private final OrderStatusService orderStatusService;

    @GetMapping()
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) throws ServiceException {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(15);

        Pageable sortedById = PageRequest.of(currentPage - 1, pageSize, Sort.by("id"));
        Page<OrderStatus> orderStatusPage = orderStatusService.findAll(sortedById);

        model.addAttribute("orderStatusPage", orderStatusPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", orderStatusPage.getTotalPages());

        model.addAttribute("orderStatus", new OrderStatus());

        int totalPages = orderStatusPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "statuses/index";
    }

    @PostMapping()
    public String create(@ModelAttribute("orderStatus") OrderStatus orderStatus)
            throws ServiceException {

        orderStatusService.save(orderStatus);
        return REDIRECT_STATUSES;
    }

    @PatchMapping("/edit")
    public String update(@ModelAttribute("orderStatus") OrderStatus orderStatus)
            throws ServiceException {

        orderStatusService.save(orderStatus);
        return REDIRECT_STATUSES;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) throws ServiceException {
        orderStatusService.deleteById(id);
        return REDIRECT_STATUSES;
    }
}
