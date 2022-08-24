package com.ivanart555.nakirunakcrm.api.controller;

import com.ivanart555.nakirunakcrm.entities.Order;
import com.ivanart555.nakirunakcrm.entities.dto.OrderDto;
import com.ivanart555.nakirunakcrm.exception.ServiceException;
import com.ivanart555.nakirunakcrm.services.CustomerService;
import com.ivanart555.nakirunakcrm.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Controller
@RequestMapping("/")
public class OrderController {
    private static final String REDIRECT_ORDERS = "redirect:/orders";
    private final OrderService orderService;
    private final CustomerService customerService;

    @GetMapping()
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) throws ServiceException {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(15);

        Pageable sortedById = PageRequest.of(currentPage - 1, pageSize, Sort.by("id"));
        Page<Order> orderPage = orderService.findAll(sortedById);

        model.addAttribute("customers", customerService.findAll());

        model.addAttribute("orderPage", orderPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", orderPage.getTotalPages());

        model.addAttribute("orderDto", new OrderDto());

        int totalPages = orderPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "orders/index";
    }

    @PostMapping()
    public String create(@ModelAttribute("order") Order order)
            throws ServiceException {
        order.setTimestamp(ZonedDateTime.now(ZoneId.of("Europe/Minsk")).toLocalDateTime());

        orderService.save(order);
        return REDIRECT_ORDERS;
    }

    @PatchMapping("/edit")
    public String update(@ModelAttribute("order") Order order)
            throws ServiceException {

        orderService.save(order);
        return REDIRECT_ORDERS;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) throws ServiceException {
        orderService.deleteById(id);
        return REDIRECT_ORDERS;
    }
}
