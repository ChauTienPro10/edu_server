package com.example.education.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.education.entities.Student;

public interface StudentRep extends JpaRepository<Student, Long>{
	
}
