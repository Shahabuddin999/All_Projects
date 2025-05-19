package com.zensar.service;

import java.util.List;

import com.zensar.json.Answer;
import com.zensar.json.Course;
import com.zensar.json.Student;

public interface StudentService {
	public Student createStudent(Student student);
	public List<Student> getAllStudents();
	Course getCourseById(Long id);
	public List<Course> getAllCourses();
}
