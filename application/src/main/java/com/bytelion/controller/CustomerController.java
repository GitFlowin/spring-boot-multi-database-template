package com.bytelion.controller;

import com.bytelion.domain.customer.model.Customer;
import com.bytelion.service.customer.CustomerServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  @Autowired
  CustomerServiceImpl customerService;

  @GetMapping(path = "/customer/all")
  public List<Customer> findAllCustomers() {
    return customerService.getAllCustomers();
  }
}
