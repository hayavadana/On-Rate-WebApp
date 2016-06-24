package com.hayavadana.rating.service;

import java.util.List;

import com.hayavadana.rating.bean.ReplyBean;

public interface ReplyService {
	public boolean saveReply(ReplyBean replyBean);
	public List<ReplyBean> getAllRepliesByTopic(Integer topicId);
}
