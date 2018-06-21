package com.springboot.basics.docket;

import io.swagger.annotations.*;

@SwaggerDefinition(info = @Info(
        description = "Awesome Resources",
        version = "V12.0.12",
        title = "Awesome Resource API",
        contact = @Contact(
                name = "Spring BootRanga Karanam",
                email = "support@spring.com",
                url = "http://start.spring.io/"
        ),
        license = @License(
                name = "Apache 2.0",
                url = "http://www.apache.org/licenses/LICENSE-2.0"
        )
),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "Read This For Sure", url = "http://in28minutes.com - https://github.com/in28minutes/spring-boot-examples"))
public interface ApiDocumentationConfig {
}
