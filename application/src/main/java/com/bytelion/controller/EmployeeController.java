package com.bytelion.controller;

import com.bytelion.domain.employee.model.Employee;
import com.bytelion.service.employee.EmployeeServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  @Autowired
  EmployeeServiceImpl employeeService;

  @GetMapping(path = "/employee/all")
  public List<Employee> findAllEmployees() {
    return employeeService.getAllEmployees();
  }
}
