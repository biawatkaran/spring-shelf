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

References:

http://www.springboottutorial.com/unit-testing-for-spring-boot-rest-services
