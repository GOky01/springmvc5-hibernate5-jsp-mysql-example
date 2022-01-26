package com.project.springmvc.controller;

import com.project.springmvc.entity.Customer;
import com.project.springmvc.service.CustomerService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ControllerAdvice
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)  // 409
//    @ExceptionHandler(Exception.class)
    @GetMapping("/listCustomers")
    public String getCustomers(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "list-customers";
    }

    @GetMapping("/addCustomer")
    public String addCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @GetMapping("/updateCustomer")
    public String updateCustomer(@RequestParam("id") int id,
                                 Model model) {
        Customer customer = customerService.getCustomer(id);
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/listCustomers";
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("id") int id) {
        customerService.deleteCustomer(id);
        return "redirect:/listCustomers";
    }
}
