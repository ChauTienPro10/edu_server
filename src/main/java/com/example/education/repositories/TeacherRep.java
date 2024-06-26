package com.example.education.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.education.entities.Teacher;



public interface TeacherRep extends JpaRepository<Teacher, Long>{
	public Teacher findByEmail(String mail);
}
