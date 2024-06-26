package com.example.education.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.education.entities.Teacher;
import com.example.education.repositories.TeacherRep;

@Service
public class TeacherSer {
	@Autowired TeacherRep teacherRep;

	
	public void save(Teacher teacher) {
		teacherRep.save(teacher);
	}

	
	
	
}
