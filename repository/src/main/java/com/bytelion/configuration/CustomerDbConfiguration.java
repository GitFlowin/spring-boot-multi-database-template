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
import org.springframework.context.annotation.Primary;
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
    basePackages = "com.bytelion.repository.customer", // Links the DataSource to all repositories under this package
    entityManagerFactoryRef = "customerDbEntityManagerFactory",
    transactionManagerRef = "customerDbTransactionManager"
)
public class CustomerDbConfiguration {

  @Bean
  @Primary
  @ConfigurationProperties("repository.datasource.customer.db")
  public DataSourceProperties customerDbDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @ConfigurationProperties("repository.datasource.customer.db.configuration")
  public DataSource customerDbDataSource() {
    return customerDbDataSourceProperties()
        .initializeDataSourceBuilder()
        .type(HikariDataSource.class)
        .build();
  }

  @Primary
  @Bean(name = "customerDbEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean customerDbEntityManagerFactory(
      EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(customerDbDataSource())
        .packages("com.bytelion.domain.customer") // Links the DataSource to all entities (domain models) under this package
        .build();
  }

  @Primary
  @Bean
  public PlatformTransactionManager customerDbTransactionManager(
      final @Qualifier("customerDbEntityManagerFactory")
          LocalContainerEntityManagerFactoryBean customerDbEntityManagerFactory) {
    return new JpaTransactionManager(
        Objects.requireNonNull(customerDbEntityManagerFactory.getObject()));
  }
}
