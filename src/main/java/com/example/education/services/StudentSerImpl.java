package com.example.education.services;

import java.awt.SecondaryLoop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.education.entities.Student;
import com.example.education.repositories.StudentRep;

@Service
public class StudentSerImpl implements StudentSer{
	
	@Autowired StudentRep studentRep;

	@Override
	public Student createStudent(Student std) {
		
		return studentRep.save(std);
	}

}
