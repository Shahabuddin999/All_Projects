package com.zensar.json;

import java.util.HashSet;
import java.util.Set;

import com.zensar.entity.StudentEntity;

public class Course {

	private long courseId;
	private String courseName;
	private Set<Student> students = new HashSet<Student>();
	public Course() {}
	
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Course(long courseId, String courseName) {
		this.courseId = courseId;
		this.courseName = courseName;
	}

	public Course(String courseName) {
		this.courseName = courseName;
	}

	public long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}