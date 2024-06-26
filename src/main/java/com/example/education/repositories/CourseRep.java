package com.example.education.repositories;

import java.util.List;

import com.example.education.entities.ThptCourse;

public interface CourseRep {
	List<ThptCourse> findAll();
}
