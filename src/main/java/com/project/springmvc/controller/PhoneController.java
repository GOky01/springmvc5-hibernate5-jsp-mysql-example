package com.project.springmvc.controller;

import com.project.springmvc.entity.Customer;
import com.project.springmvc.entity.Phone;
import com.project.springmvc.service.CustomerService;
import com.project.springmvc.service.PhoneService;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PhoneController {

    private final CustomerService customerService;
    private final PhoneService phoneService;

    public PhoneController(CustomerService customerService, PhoneService phoneService) {
        this.customerService = customerService;
        this.phoneService = phoneService;
    }

    @GetMapping("/addPhone")
    public String addPhone(Model model) {
        Phone phone = new Phone();
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("phone", phone);
        model.addAttribute("customers", customers);
        return "phone-form";
    }

    @PostMapping("/savePhone")
    public String savePhone(@ModelAttribute("phone") Phone phone,
                            @RequestParam int id)  {
        Customer customer = customerService.getCustomer(id);
        phone.setCustomer(customer);
        phoneService.savePhone(phone);
        return "redirect:/listCustomers";
    }
}
