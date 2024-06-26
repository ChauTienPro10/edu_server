package com.example.education.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.education.entities.ThptCourse;

@Repository
public interface ThptCourseRepository extends JpaRepository<ThptCourse, Long>{
	public interface ThptCourseProjection {
	    Long getId();
	    String getName();
	    int getLevel();
	    String getTeacherName();
	}
	List<ThptCourseProjection> findAllProjectedBy();
}
