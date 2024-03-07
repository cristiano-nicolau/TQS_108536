# a) Identify a couple of examples that use AssertJ expressive methods chaining.


1. **A_EmployeeRepositoryTest:**
```java
assertThat(found).isEqualTo(alex);
assertThat(fromDb.getEmail()).isEqualTo(emp.getEmail());
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());

```

2. **B_EmployeeService_UnitTest:**
```java
assertThat(found.getName()).isEqualTo(name);
```

3. **E_EmployeeRestControllerTemplateIT:**
```java
assertThat(found).extracting(Employee::getName).containsOnly("bob");
```


# b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).

1. **B_EmployeeService_UnitTest:**
```java
    @BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Employee john = new Employee("john", "john@deti.com");
        john.setId(111L);

        Employee bob = new Employee("bob", "bob@deti.com");
        Employee alex = new Employee("alex", "alex@deti.com");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
    }
```


# c) What is the difference between standard @Mock and @MockBean?

**@Mock:**
- This annotation is used to create a mock object of a class or an interface.
- It is used in unit testing.
- It is used to mock the behavior of a class or an interface.


**@MockBean:**
- This annotation is used to add mock objects to the Spring application context.
- It is used in integration testing.
- It is used to mock the behavior of a class or an interface.


# d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

- The file “application-integrationtest.properties” is used to configure the application for integration testing and it is used when we want to run integration tests.


# e) he sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences? 

In C, the application is not fully loaded. Only the controller is being tested, so there is no need to load the services and repositories. MockMvc is used to mock the other components.
In D and E, the application is fully loaded. However, in D, one accesses the server context through a special testing servlet (MockMvc object) while in E the requester is a REST client (TestRestTemplate).
