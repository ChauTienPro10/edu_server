package com.example.education.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_teacher")
public class Teacher {
	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;
    @Column(name="major") // ex. ly hoa sinh  toan ,.....\
	private String major;
    
    @OneToMany(mappedBy = "teacher")
    private List<ThptCourse> courses;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public List<ThptCourse> getCourses() {
		return courses;
	}

	public void setCourses(List<ThptCourse> courses) {
		this.courses = courses;
	}

	public Teacher(String name, String email, String major, List<ThptCourse> courses) {
		super();
		this.name = name;
		this.email = email;
		this.major = major;
		this.courses = courses;
	}

	public Teacher() {
		super();
	}
    
	

}
