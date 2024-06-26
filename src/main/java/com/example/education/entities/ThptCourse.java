package com.example.education.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_thpt_course")
public class ThptCourse {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	@Column(name="level") // lop 11 ,12,...
	private int level;
	@Column(name="link")
	private String link;
	
	@ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public ThptCourse(String name, int level,String link, Teacher teacher) {
		super();
		this.name = name;
		this.level = level;
		this.link=link;
		this.teacher = teacher;
	}

	public ThptCourse() {
		super();
	}
	
	
}
