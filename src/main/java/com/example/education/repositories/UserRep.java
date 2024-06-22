package com.example.education.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.education.entities.User;

public interface UserRep extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
	
}
