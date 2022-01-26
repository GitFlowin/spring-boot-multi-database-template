package com.bytelion.service.customer;

import com.bytelion.domain.customer.model.Customer;
import com.bytelion.domain.customer.service.CustomerService;
import com.bytelion.repository.customer.CustomerRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  CustomerRepository customerRepository;

  // Helper method to seed some test data
  @PostConstruct
  public void seedData() {
    customerRepository.saveAll(Stream.of(
        new Customer(1L, "Seong", "Customer"),
        new Customer(2L, "John", "Customer"))
        .collect(Collectors.toList()));
  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }
}
