package com.example.education.Authentication;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.education.services.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired JwtTokenProvider tokenProvider;
	@Autowired CustomUserDetailsService userService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = getJwtFromRequest(request);
			 if (org.springframework.util.StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				 String userName = tokenProvider.getUserNameFromJWT(jwt);
				 UserDetails userDetails = userService.loadUserByUsername(userName);
				 if(userDetails != null) {
					 UsernamePasswordAuthenticationToken
                     authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					 SecurityContextHolder.getContext().setAuthentication(authentication);
				 }
			 }
			 System.out.printf("success on set user authentication" );
		}
		catch (Exception e) {
			 System.out.printf("failed on set user authentication", e);
		}
		filterChain.doFilter(request, response);
	}
	private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        System.out.print(request.getHeader("Authorization"));
        if (org.springframework.util.StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
