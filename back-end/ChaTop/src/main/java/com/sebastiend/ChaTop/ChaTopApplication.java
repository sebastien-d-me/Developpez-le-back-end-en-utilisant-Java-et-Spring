package com.sebastiend.ChaTop;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;


@OpenAPIDefinition(info = @Info(title = "API Chatop", description = "API for the Chatop Angular application.", version = "V1.0"))
@SpringBootApplication
public class ChaTopApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChaTopApplication.class, args);
	}
}
