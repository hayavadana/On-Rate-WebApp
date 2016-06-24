package com.hayavadana.rating.dao;

import java.util.List;

import com.hayavadana.rating.model.Topic;

public interface TopicDao {
	public Topic saveTopic(Topic topic);
	public List<Topic> getAllTopicsByUser(Integer userId);
}
