package com.zensar.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.entity.CourseEntity;
import com.zensar.entity.StudentEntity;
import com.zensar.json.Course;
import com.zensar.json.Student;
import com.zensar.repo.CourseRepo;
import com.zensar.repo.StudentRepo;
import com.zensar.util.StudentUtil;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	CourseRepo courseRepo;
	
	@Autowired
	ModelMapper modelMapper;
	@Override
	public Student createStudent(Student student) {
		StudentEntity studentEntity = StudentUtil.convertStudentIntoStudentEntity(student);
		studentEntity = studentRepo.save(studentEntity);
		return StudentUtil.convertStudentEntityIntoStudent(studentEntity);
	}

	@Override
	public List<Student> getAllStudents() {
		return StudentUtil.convertStudentEntityListIntoStudentList(studentRepo.findAll());
	}

	@Override
	public List<Course> getAllCourses() {
		List<CourseEntity> list = courseRepo.findAll();
		List<Course> courses = new ArrayList<>();
		for(CourseEntity courseEntity : list) 
			courses.add(getCourseById(courseEntity));
		return courses;
	}
	
	@Override
	public Course getCourseById(Long id) {
		Optional<CourseEntity> courseEntity = courseRepo.findById(id);
		return getCourseById(courseEntity.get());
		
	}
	
	public Course getCourseById(CourseEntity courseEntity) {
		Set<Student> set = new LinkedHashSet<>();
		Course course = null;
		for(StudentEntity studentEntity : courseEntity.getStudents()) {
			Student student = modelMapper.map(studentEntity, Student.class);
			student.setCourses(new LinkedHashSet<>());
			set.add(student);
		}
		course = modelMapper.map(courseEntity, Course.class);
		course.setStudents(set);
		return course;
	}
		
	}
