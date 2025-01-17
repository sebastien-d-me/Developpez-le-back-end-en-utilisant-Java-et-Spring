package com.sebastiend.ChaTop.configurations;


import com.nimbusds.jose.jwk.source.ImmutableSecret;
import io.swagger.v3.oas.annotations.enums.*;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@SecurityScheme(
    bearerFormat = "JWT", 
    in = SecuritySchemeIn.HEADER,
    name = "bearerAuth",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP
)
public class SpringSecurityConfig {
    @Value("${jwt.key}")
    private String JWTKey;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {		
		return http
		    .csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/register", "/api/auth/login", "/error", "/v3/**", "/uploads/**").permitAll()
                .anyRequest().authenticated())
            .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
			.build();		
	}
    
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKey = new SecretKeySpec(this.JWTKey.getBytes(), 0, this.JWTKey.getBytes().length, "RSA");
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
    }

    @Bean
	JwtEncoder jwtEncoder() {
		return new NimbusJwtEncoder(new ImmutableSecret<>(this.JWTKey.getBytes()));
	}
}