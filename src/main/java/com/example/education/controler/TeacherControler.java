package com.example.education.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.education.entities.Teacher;
import com.example.education.entities.ThptCourse;
import com.example.education.services.TeacherSer;

@RestController
@RequestMapping("/teacher")
public class TeacherControler {
	@Autowired TeacherSer teacherSer;
	
	@PostMapping("/new")
	public String newTeacher(@RequestBody Map<String, String> jsonData) {
		try {
			String name=jsonData.get("name");
			String email=jsonData.get("email");
			String major=jsonData.get("major");
			List<ThptCourse> courses=new ArrayList<ThptCourse>();
			Teacher teacher=new Teacher(name,email,major,courses );
			teacherSer.save(teacher);
			return "YES";
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return "NO";
		}
	}
}
