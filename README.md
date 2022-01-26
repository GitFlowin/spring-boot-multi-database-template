# Spring Boot API Template Project
##### (Multi-Module Structure with Multiple DataSources)

### This template project can be used as a starting point for a multi-module, multi-database structure of a Spring Boot API.  

---

## Project Modules

#### Application 🚀

    The application module will be the entry point into our REST API.

    The endpoints will be defined under our controllers for each domain model.

#### Service 🕹

    The service module will be used to implement the service layer which is responsible for 
    implementing any business logic for our data.

    We will also pass through any repository calls through our service layer, 
    even if there is no business logic.

#### Repository 🔋

    The repository module will be used to create our repository interfaces for each domain
    entity in our application.

#### Domain 🏫

    The domain module will be used to define our domain models as well 
    as our service interface(s), as they should align with 
    the domain's business logic

---

## Installation

You will need to download and install the following:

* [Java 11 SDK](https://adoptopenjdk.net/index.html?variant=openjdk11&jvmVariant=hotspot)
  * Download and install, don’t forget to set your PATH variable!
  * I recommend using [Homebrew to download your Java SDK and JEnv to manage your path and versions](https://chamikakasun.medium.com/how-to-manage-multiple-java-version-in-macos-e5421345f6d0)

* [Maven](https://maven.apache.org/)
  * Download and install, don’t forget to set your [PATH variable!](https://maven.apache.org/install.html)
    
* [PostgreSQL](https://www.postgresql.org/download/)

---

## Configure

* Start up your PostGres Database on the default port (5432) and create 2 databases called:
  * `customerTestDb`
  * `employeeTestDb`
* See `repository.properties` for more details on the database configuration (In the repository module resources)

---

## Starting the API
* Go to the base directory of the parent project and run the command:
```
mvn clean install && java -jar application/target/application-0.0.1-SNAPSHOT.jar
```

* Once your application has started without any errors, you will be able to run the endpoints from `localhost:8080` 
  * Checkout the controller classes in the application module for all available endpoints.

  * Example: 
    * ```
      Request: 
      =========
      GET localhost:8080/customer/all
      
      Response: 
      =========
      [
        {
          "id": 1,
          "firstName": "Seong",
          "lastName": "Customer"
        },
        {
          "id": 2,
          "firstName": "John",
          "lastName": "Customer"
        }
      ]
      ```

* Notes:
  * `mvn clean install`
    * Compiles our code and packages it into a runnable format in each module's `/target` directory (in our case, a jar) AND packages it to our local m2 repository.
    * For more info, check out the various [Maven build lifecycles](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
  
  * `java -jar application/target/application-0.0.1-SNAPSHOT.jar` 
    * Runs the API locally
  
  * > Depending on which `repository` is being used in our service layer, it will make transactions against appropriate databases, `testDb1` or `testDb2`, allowing us to configure n number of databases as we need.
    
