package com.sebastiend.ChaTop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Chatop", description = "API for the Chatop Angular application.", version = "V1.0"))
public class ChaTopApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChaTopApplication.class, args);
	}
}
