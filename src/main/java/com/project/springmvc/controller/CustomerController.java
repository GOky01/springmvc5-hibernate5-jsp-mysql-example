package com.project.springmvc.controller;

import com.project.springmvc.entity.Customer;
import com.project.springmvc.entity.Phone;
import com.project.springmvc.service.CustomerService;
import com.project.springmvc.service.PhoneService;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final PhoneService phoneService;

    public CustomerController(CustomerService customerService, PhoneService phoneService) {
        this.customerService = customerService;
        this.phoneService = phoneService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listCustomers(Model theModel) {
        List<Customer> theCustomers = customerService.getCustomers();
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        Customer theCustomer = new Customer();
        theModel.addAttribute("customer", theCustomer);
        return "customer-form";
    }

    @GetMapping("/showPhoneForm")
    public String showPhoneFormForAdd(Model theModel) {
        Phone phone = new Phone();
        List<Customer> customers = customerService.getCustomers();
        Customer customer = new Customer();
        theModel.addAttribute("phone", phone);
        theModel.addAttribute("customers", customers);
        return "phone-form";
    }

    @PostMapping("/savePhone")
    public String savePhone(@ModelAttribute("phone") Phone phone, BindingResult bindingResult) {
        phoneService.savePhone(phone);
        return "redirect:/customer/list";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
        customerService.saveCustomer(theCustomer);
        return "redirect:/customer/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("customerId") int theId,
                                    Model theModel) throws NotFoundException {
        Customer theCustomer = customerService.getCustomer(theId);
        theModel.addAttribute("customer", theCustomer);
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {
        customerService.deleteCustomer(theId);
        return "redirect:/customer/list";
    }
}
