package com.hayavadana.rating.service;

import java.util.List;

import com.hayavadana.rating.bean.TopicBean;

public interface TopicService {
	public boolean saveTopic(TopicBean topicBean);

	public List<TopicBean> getAllTopicsByUser(Integer userId);
}
