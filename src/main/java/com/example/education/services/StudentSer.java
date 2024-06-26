package com.example.education.services;



import org.springframework.stereotype.Service;

import com.example.education.entities.Student;

@Service
public interface StudentSer {
	
	public Student createStudent(Student std);
}
