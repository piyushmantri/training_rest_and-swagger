package com.endurance.emdb.Swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket Api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("EMDB")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.endurance.emdb.Controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("EMDB")
                .description("Endurance Spring Boot Practice App")
                .contact("Piyush Mantri")
                .build();
    }
}
