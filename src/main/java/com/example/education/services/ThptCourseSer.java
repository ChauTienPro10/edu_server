package com.example.education.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.education.entities.Teacher;
import com.example.education.entities.ThptCourse;
import com.example.education.repositories.TeacherRep;
import com.example.education.repositories.ThptCourseRepository;
import com.example.education.repositories.ThptCourseRepository.ThptCourseProjection;

@Service
public class ThptCourseSer {
	@Autowired ThptCourseRepository thptCourseRepository;
	@Autowired TeacherRep teacherRep;
	public void save(ThptCourse thpt,String teacherMail) {
		Teacher teacher=teacherRep.findByEmail(teacherMail);
		thpt.setTeacher(teacher);
		thptCourseRepository.save(thpt);
	}
	public List<ThptCourseProjection> getAll(){
		try {
			return thptCourseRepository.findAllProjectedBy();
			
		}
		catch (Exception e) {
			System.out.print(e.toString());
			return null;
		}
	}
}
