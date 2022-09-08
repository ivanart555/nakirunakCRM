package com.ivanart555.nakirunakcrm.api.controller;

import com.ivanart555.nakirunakcrm.api.mapper.OrderMapper;
import com.ivanart555.nakirunakcrm.entities.Order;
import com.ivanart555.nakirunakcrm.entities.dto.OrderDto;
import com.ivanart555.nakirunakcrm.exception.ServiceException;
import com.ivanart555.nakirunakcrm.services.CustomerService;
import com.ivanart555.nakirunakcrm.services.DestinationService;
import com.ivanart555.nakirunakcrm.services.OrderService;
import com.ivanart555.nakirunakcrm.services.OrderStatusService;
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
@RequestMapping("/orders")
public class OrderController {
    private static final String REDIRECT_ORDERS = "redirect:/orders";
    private final OrderService orderService;
    private final CustomerService customerService;
    private final DestinationService destinationService;
    private final OrderStatusService orderStatusService;
    private final OrderMapper orderMapper;

    @GetMapping()
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) throws ServiceException {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(15);

        Pageable sortedById = PageRequest.of(currentPage - 1, pageSize, Sort.by("publicId"));
        Page<Order> orderPage = orderService.findAll(sortedById);

        Page<OrderDto> orderDtoPage = orderPage.map(orderMapper::convertToDto);

        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("destinations", destinationService.findAll());
        model.addAttribute("orderStatuses", orderStatusService.findAll());

        model.addAttribute("orderDtoPage", orderDtoPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", orderDtoPage.getTotalPages());

        model.addAttribute("orderDto", new OrderDto());

        int totalPages = orderDtoPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "orders/index";
    }

    @PostMapping()
    public String create(@ModelAttribute("orderDto") OrderDto orderDto)
            throws ServiceException {
        Order order = orderMapper.convertToEntity(orderDto);
        orderService.assignPublicId(order);

        orderService.save(order);
        return REDIRECT_ORDERS;
    }

    @PatchMapping("/edit")
    public String update(@ModelAttribute("orderDto") OrderDto orderDto)
            throws ServiceException {
        Order order = orderMapper.convertToEntity(orderDto);
        orderService.save(order);
        return REDIRECT_ORDERS;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) throws ServiceException {
        orderService.deleteById(id);
        return REDIRECT_ORDERS;
    }
}
