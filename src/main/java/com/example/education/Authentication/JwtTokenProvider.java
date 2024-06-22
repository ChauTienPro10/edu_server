package com.example.education.Authentication;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {
	private final String jwt_secret="22012003";
	private final long JWT_EXPIRATION = 3600000L;
	public String generateToken(CustomUserDetails customUserDetails) {
		
		Date now= new Date();
		Date expiryDay=new Date(now.getTime()+JWT_EXPIRATION);
		return Jwts.builder().setSubject(customUserDetails.getUsername())
				.setIssuedAt(now)
				.setExpiration(expiryDay)
				.signWith(SignatureAlgorithm.HS512, jwt_secret).compact();
	}
	public String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(jwt_secret)
                            .parseClaimsJws(token)
                            .getBody();

        return claims.getSubject();
    }
	 public boolean validateToken(String authToken) {
	        try {
	            Jwts.parser().setSigningKey(jwt_secret).parseClaimsJws(authToken);
	            return true;
	        } catch (MalformedJwtException ex) {
	           System.out.print("Invalid JWT token");
	        } catch (ExpiredJwtException ex) {
	        	System.out.print("Expired JWT token");
	        } catch (UnsupportedJwtException ex) {
	        	System.out.print("Unsupported JWT token");
	        } catch (IllegalArgumentException ex) {
	        	System.out.print("JWT claims string is empty.");
	        }
	        return false;
	    }
	
}
