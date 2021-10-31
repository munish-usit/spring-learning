package org.spring.courseapi.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;
	//private TopicService topicService;
	
	
	
	@RequestMapping("/topics/{topicId}/courses")
	public List<Course> getTopics(@PathVariable("topicId") int topicId) {
		return courseService.getAllCourses(topicId);
	}
	
	@RequestMapping("/topics/{topicId}/courses/{id}")
	public Course getTopic(@PathVariable("id") int id) {
		return courseService.getCourse(id);
	}
	
	// In this implementation we are itself adding topicId base on the url /topics/{topicId}
	// Other approach could be that user itself provide topicId in the course json structure
	@RequestMapping(method=RequestMethod.POST,value="/topics/{topicId}/courses")
	public List<Course> addTopic(@RequestBody Course course, @PathVariable("topicId") int topicId) {
		course.setTopicid(topicId);
		courseService.addCourse(course);
		return courseService.getAllCourses();  // optional to see updated result
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/topics/{topicId}/courses/{id}")
	public List<Course> updateCourse(@RequestBody Course course,@PathVariable("topicId") int topicId) {
		course.setTopicid(topicId);
		courseService.updateCourse(course);
		return courseService.getAllCourses();  // optional to see updated result
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/topics/{topicId}/courses/{id}")
	public List<Course> deleteCourse(@PathVariable("id") int id) {
		courseService.deleteCourse(id);
		return courseService.getAllCourses();  // optional to see updated result
	}
	
	
}
