package org.spring.courseapi.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

	@Autowired
	private TopicDataService topicService;
	//private TopicService topicService;
	
	@RequestMapping("/topics")
	public List<Topic> getTopics() {
		return topicService.getAllTopics();
	}
	
	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable("id") int id) {
		return topicService.getTopic(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/topics")
	public List<Topic> addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
		return topicService.getAllTopics();  // optional to see updated result
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/topics/{id}")
	public List<Topic> updateTopic(@RequestBody Topic topic,@PathVariable("id") int id) {
		topicService.updateTopic(topic, id);
		return topicService.getAllTopics();  // optional to see updated result
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/topics/{id}")
	public List<Topic> deleteTopic(@PathVariable("id") int id) {
		topicService.deleteTopic(id);
		return topicService.getAllTopics();  // optional to see updated result
	}
	
	
}
