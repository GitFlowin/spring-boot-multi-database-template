package com.bytelion.configuration;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = "repository.properties")
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.bytelion.repository.employee", // Links the DataSource to all repositories under this package
    entityManagerFactoryRef = "employeeDbEntityManagerFactory",
    transactionManagerRef = "employeeDbTransactionManager"
)
public class EmployeeDbConfiguration {

  @Bean
  @ConfigurationProperties("repository.datasource.employee.db")
  public DataSourceProperties employeeDbDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @ConfigurationProperties("repository.datasource.employee.db.configuration")
  public DataSource employeeDbDataSource() {
    return employeeDbDataSourceProperties()
        .initializeDataSourceBuilder()
        .type(HikariDataSource.class)
        .build();
  }

  @Bean(name = "employeeDbEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean employeeDbEntityManagerFactory(
      EntityManagerFactoryBuilder builder
  ) {
    return builder
        .dataSource(employeeDbDataSource())
        .packages("com.bytelion.domain.employee") // Links the DataSource to all entities (domain models) under this package
        .build();
  }

  @Bean
  public PlatformTransactionManager employeeDbTransactionManager(
      final @Qualifier("employeeDbEntityManagerFactory")
          LocalContainerEntityManagerFactoryBean employeeDbEntityManagerFactory) {
    return new JpaTransactionManager(
        Objects.requireNonNull(employeeDbEntityManagerFactory.getObject()));
  }
}
