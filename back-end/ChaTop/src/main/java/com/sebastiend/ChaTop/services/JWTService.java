package com.sebastiend.ChaTop.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.sebastiend.ChaTop.models.entities.UserEntity;


@Service
public class JWTService {
	private JwtEncoder jwtEncoder;
	
	public JWTService(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}
	
	/*public String generateToken(Authentication authentication) {
        System.out.println(authentication);
        Instant now = Instant.now();
     	JwtClaimsSet claims = JwtClaimsSet.builder()
            .claim("email", authentication.getName())
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(1, ChronoUnit.DAYS))
            .subject(authentication.getName())
            .build();
		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
		String JWTtoken =  this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        return JWTtoken;
    }*/

    public String generateTokenRegister(UserEntity user) {
        Instant now = Instant.now();
     	JwtClaimsSet claims = JwtClaimsSet.builder()
            .claim("email", user.getEmail())
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(1, ChronoUnit.HOURS))
            .subject(user.getEmail())
            .build();
		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
		String JWTtoken =  this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        return JWTtoken;
    }

    public String generateTokenLogin(UserEntity user) {
        Instant now = Instant.now();
     	JwtClaimsSet claims = JwtClaimsSet.builder()
            .claim("email", user.getEmail())
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(1, ChronoUnit.HOURS))
            .subject(user.getEmail())
            .build();
		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
		String JWTtoken =  this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        return JWTtoken;
    }
}
