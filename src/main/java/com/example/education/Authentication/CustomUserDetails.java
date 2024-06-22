package com.example.education.Authentication;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.education.entities.User;

public class CustomUserDetails implements UserDetails{
	
	private final User user;
	public CustomUserDetails(User user) {
        this.user = user;
    }
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {

		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getEmail();
	}

}
