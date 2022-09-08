package com.ivanart555.nakirunakcrm.api.controller;

import com.ivanart555.nakirunakcrm.entities.Customer;
import com.ivanart555.nakirunakcrm.exception.ServiceException;
import com.ivanart555.nakirunakcrm.services.CustomerService;
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
@RequestMapping("/customers")
public class CustomerController {
    private static final String REDIRECT_CUSTOMERS = "redirect:/customers";
    private final CustomerService customerService;

    @GetMapping()
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) throws ServiceException {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(15);

        Pageable sortedById = PageRequest.of(currentPage - 1, pageSize, Sort.by("publicId"));
        Page<Customer> customerPage = customerService.findAll(sortedById);

        model.addAttribute("customerPage", customerPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", customerPage.getTotalPages());

        model.addAttribute("customer", new Customer());

        int totalPages = customerPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "customers/index";
    }

    @PostMapping()
    public String create(@ModelAttribute("customer") Customer customer)
            throws ServiceException {
        customerService.assignPublicId(customer);

        customerService.save(customer);
        return REDIRECT_CUSTOMERS;
    }

    @PatchMapping("/edit")
    public String update(@ModelAttribute("customer") Customer customer)
            throws ServiceException {
        customerService.save(customer);
        return REDIRECT_CUSTOMERS;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) throws ServiceException {
        customerService.deleteById(id);
        return REDIRECT_CUSTOMERS;
    }
}
