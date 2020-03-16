package org.spring.courseapi.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	List<Topic> topics = new ArrayList<Topic>(Arrays.asList(
			new Topic(1,"springboot","springbootquickstart"),
			new Topic(2,"microservices","microservice")
			));
	
	public List<Topic> getAllTopics() {
		return topics;
		
	}
	
	public Topic getTopic(int id) {
		return topics.stream().filter(t -> t.getId() == id).findFirst().get();
	}
	
	public void addTopic(Topic topic) {
		topics.add(topic);
	}
	
	public void updateTopic(Topic topic, int id) {
		for(int i=0;i<topics.size();i++) {
			if(topics.get(i).getId() == id) {
				topics.set(i, topic);
				return;
			}
		}
			
	}
	
	public void deleteTopic(int id) {
		topics.removeIf(t->t.getId()==id);
	}
	

}
