package com.bytelion.domain.employee.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity@Table(name = "employees")
public class Employee {

  @Id
  private Long id;

  private String firstName;

  private String lastName;
}
