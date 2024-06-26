package com.example.education.controler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.education.entities.ThptCourse;
import com.example.education.repositories.ThptCourseRepository.ThptCourseProjection;
import com.example.education.services.ThptCourseSer;

@RestController
@RequestMapping("/course")
public class CourseControler {
	@Autowired ThptCourseSer thptCourseSer;
	@PostMapping("new")
	public String newThptCourse(@RequestBody Map<String, String> jsonData) {
		try {
			String name=jsonData.get("name").toUpperCase();
			int level=Integer.parseInt(jsonData.get("level"));
			String emailTeacher=jsonData.get("email"); //mail of teacher
			ThptCourse thptCourse=new ThptCourse(name, level, null);
			thptCourseSer.save(thptCourse, emailTeacher);
			return "YES";
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return "NO";
		}
	}
	@GetMapping("/getCourse")
	public List<ThptCourseProjection> getCourse(){
		try {
			return thptCourseSer.getAll();
		}
		catch (Exception e) {
			System.out.print(e.toString());
			return null;
		}
	}
}
