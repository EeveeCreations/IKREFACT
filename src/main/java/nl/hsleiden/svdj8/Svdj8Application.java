package nl.hsleiden.svdj8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class Svdj8Application {

	public static void main(String[] args) {
		SpringApplication.run(Svdj8Application.class, args);
	}
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("nl.hsleiden.svdj8.controllers"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfo(
				"Svdj groep 8",
				"Spring Boot REST API for Svdj Application",
				"1.0",
				"Terms of service",
				new Contact("Bla Bla", "https://springframework.guru/about/", "bla@bla.nl"),
				"Apache License Version 2.0",
				"https://www.apache.org/licenses/LICENSE-2.0",
				Collections.emptyList());
	}
}
