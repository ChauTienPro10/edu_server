package com.example.education.controler;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.csrf.CsrfTokenRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.education.Authentication.CustomUserDetails;
import com.example.education.Authentication.JwtTokenProvider;
import com.example.education.Authentication.LoginResponse;
import com.example.education.entities.Student;
import com.example.education.entities.User;
import com.example.education.repositories.UserRep;
import com.example.education.services.StudentSerImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserControler {
	@Autowired
	StudentSerImpl studentSerImpl;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRep userRep;
	@Autowired
	JwtTokenProvider tokenProvider;
	@Autowired
	private CsrfTokenRepository csrfTokenRepository;

	@PostMapping("/newStudent")
	public String createStudent(@RequestBody Map<String, String> jsonDta) {
		try {
			String fullname = jsonDta.get("name");
			String email = jsonDta.get("email");
			String password = jsonDta.get("password");
			String passEncode = passwordEncoder.encode(password);
			String phone = jsonDta.get("phone");
			User user = new User( passEncode, email);
			Student std = new Student(fullname, email, phone, user);
			studentSerImpl.createStudent(std);
			return "yes";
		} catch (Exception e) {

			return "no";
		}
	}

	@PostMapping("/login")
	public LoginResponse login(@RequestBody Map<String, String> jsondta, jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response) throws AuthenticationException {

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(jsondta.get("username"),
				jsondta.get("password"));
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
			List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
			Optional<User> us = userRep.findByEmail(jsondta.get("username"));
			
//			HttpSession session = request.getSession();
//			session.setAttribute("JWT_TOKEN", jwt);
			return new LoginResponse(jwt, authorities, authentication.getName(), us.get().getId());
		} catch (AuthenticationException e) {

			e.printStackTrace();
			return null;
		}

	}
	@PostMapping("/logout")
	public ResponseEntity<?> logout(jakarta.servlet.http.HttpServletRequest request) {

		SecurityContextHolder.clearContext();// xoa tat ca thong tin nguoi dung khoi phien hien tai

		return ResponseEntity.ok().build();
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	@PostMapping("/hello")
	public String hello1() {
		return "hello";
	}
}
