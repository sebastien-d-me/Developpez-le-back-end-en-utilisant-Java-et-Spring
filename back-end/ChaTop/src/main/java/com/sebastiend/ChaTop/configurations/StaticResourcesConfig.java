package com.sebastiend.ChaTop.configurations;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class StaticResourcesConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/rentals/**")
            .addResourceLocations("file:src/main/resources/static/uploads/rentals/");
    }
}