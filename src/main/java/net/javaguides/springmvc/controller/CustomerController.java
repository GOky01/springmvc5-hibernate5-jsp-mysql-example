package net.javaguides.springmvc.controller;

import java.util.List;

import net.javaguides.springmvc.entity.Phone;
import net.javaguides.springmvc.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springmvc.entity.Customer;
import net.javaguides.springmvc.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private final CustomerService customerService;
	private final PhoneService phoneService;

	public CustomerController(CustomerService customerService, PhoneService phoneService) {
		this.customerService = customerService;
		this.phoneService = phoneService;
	}

	@GetMapping("/list")
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
		List<Customer> theCustomers = customerService.getCustomers();
		theModel.addAttribute("customers", theCustomers);
		theModel.addAttribute("phone", phone);
		return "phone-form";
	}

	@PostMapping("/savePhone")
	public String savePhone(@ModelAttribute("phone") Phone phone)
//							@ModelAttribute("customers") Customer customer) {
	{
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
									Model theModel) {
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
