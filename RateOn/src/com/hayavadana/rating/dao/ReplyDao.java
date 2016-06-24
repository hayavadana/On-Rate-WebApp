package com.hayavadana.rating.dao;

import java.util.List;

import com.hayavadana.rating.model.Reply;

public interface ReplyDao {
	public Reply saveReply(Reply reply);
	public List<Reply> getAllRepliesByTopic(Integer topicId);
}
