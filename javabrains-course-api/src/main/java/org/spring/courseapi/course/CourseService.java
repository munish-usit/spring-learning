package org.spring.courseapi.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	public List<Course> getAllCourses() {
		List<Course> courses = new ArrayList<Course>();
		courseRepository.findAll().forEach(c -> courses.add(c));
		return courses;
	}
	
	public List<Course> getAllCourses(int topicId) {
		List<Course> courses = new ArrayList<Course>();
		courseRepository.findByTopicid(topicId).forEach(c -> courses.add(c));
		return courses;
	}
	
	public Course getCourse(int id) {
		
		return courseRepository.findById(id).orElse(null);
	}
	
	public void addCourse(Course course) {
		courseRepository.save(course);
	}
	
	public void updateCourse(Course course) {
		courseRepository.save(course);
	}
	
	public void deleteCourse(int id) {
		courseRepository.deleteById(id);
	}
}
