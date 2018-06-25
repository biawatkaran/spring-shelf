# Spring-Shelf
Spring mini projects




Additional Information
1. Component for defining beans and dependencies (i.e. use Autowire on them)
-- Mainly inside @SpringBootApplication main class below ones
2. Use @Configuration for property file along with  @PropertySource({"classpath:XXX.properties"})
3. @EntityScan for model classes for db stuff
4. @Import in order to import particular class not defined in scan annotations as below
5. @ComponentScan with base Package having package locations
-- General ones
6. @RestController @RequestMapping (to define the base url context) for controllers, @GetMapping @PostMapping @ResponseBody etc underneath
7. @ControllerAdvice along with @ExceptionHandler in exception controller class in web projects, @Data to be used ExceptonResponse
8. @Service to be used on service implementation which is not interacting with db directly
9. on db models use @Entity @Table @Column @Id etc
10. @Repository the class which calls db operations, with methods having @Transactional and defining isolation levels READ ONLY etc

Misc
1. mvn help:effective-settings, help:effective-pom, dependency:tree, dependency:sources, --debug
2. @Mock to mock the interface or calls, now that mock will need to go to class which needs it probably via its constructor, so in test class we can use @InjectMocks
    e.g.
    @Mock                                       @InjectMocks                        @RunWith(MockitoJUnitRunner.class)
    DataService dataServiceMock                 SomeBusinessImpl businessImpl       SomeBusinessTest

    when(dataServiceMock.someMethod()).thenReturn(someValue)
3. In spring boot tests we can have something similar

    Unit Tests

    <code>
     @MockBean (for the service layer),                     @RunWith(SpringRunner.class)
     @Autowired for MockMvc so to perform the execution     @WebMvcTest(value = LoginController.class, secure = false)
    </code>

    Integration Test

    <code>
    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = LoginApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

    @LocalServerPort
    restTemplate.exchange(createURLWithPort("/login"), HttpMethod.GET, entity, String.class);
    </code>
4. We can also use @ConfigurationProperties("basic) as @Component with getter/setter, later can be used as @Autowired. Properties can be set in application.properties
   such as basic.someVar=value. This is typesafe meaning spring will fail at startup time if allowed type of values are not filled in e.g. string being filled in int type
5. Content-Type = content type of the body of the reqeuest, Accept =  expected content type of the response
6. MS1 --calling--> MS2 We were hardcoding the MS2 url inside MS1 even by using Fiegn Proxy -> MS2

   Scaling

        *Feign* for rest clients => scaling
        *Ribbon* is used for client side load balancing = In above call MS1 hard code the MS2 url so if more instance of MS2 raised
                 we can't balance them or call the second MS2 instance because running or different port, hence ribbon come into picture.
                 In application property file we can have forex-service.ribbon.listOfServers=localhost:8000,localhost:8001

        *Eureka* is for registering the micro-services (Naming server). The above problem gets sorted out here, as we register MSs here

   Monitoring & Visibility

   Zipkin distributed tracing, Netflix gateway

   Spring cloud config server => configuration management
   Hystrix => fault tolerance

References:

Spring boot test: http://www.springboottutorial.com/unit-testing-for-spring-boot-rest-services

App properties: https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html

Swagger Definition: http://localhost:8080/v2/api-docs & http://localhost:8080/swagger-ui.html

Rest API versioning: http://www.springboottutorial.com/spring-boot-versioning-for-rest-services

