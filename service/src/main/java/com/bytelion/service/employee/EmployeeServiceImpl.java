package com.bytelion.service.employee;

import com.bytelion.domain.employee.model.Employee;
import com.bytelion.domain.employee.service.EmployeeService;
import com.bytelion.repository.employee.EmployeeRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  // Helper method to seed some test data
  @PostConstruct
  public void seedData() {
    employeeRepository.saveAll(Stream.of(
        new Employee(1L, "Seong", "Employee"),
        new Employee(2L, "John", "Employee"))
        .collect(Collectors.toList()));
  }

  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }
}
