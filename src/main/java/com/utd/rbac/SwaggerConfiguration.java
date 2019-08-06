package com.utd.rbac;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.utd.rbac.controller"))
                .paths(PathSelectors.regex("/v1.*"))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(metaData());
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
 
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

	private ApiInfo metaData() {
	    return new ApiInfoBuilder()
	            .title("Role Based Access Management API Definition")
	            .description("Role Based Access Management API's for the following:"
	                    + "\n\t\t 1. Post Roles"
	                    + "\n\t\t 2. Post Users"
	                    + "\n\t\t 3. Get Users")
	            .contact(new Contact("Amal Roy", "","mailto::amal.roy@utdallas.edu"))
	            .build();
	}
}