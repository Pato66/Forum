package com.example.forum.repository;

import java.util.List;

import com.example.forum.model.Message;
import com.example.forum.model.Topic;
import com.example.forum.model.User;


public interface ClientRepository {
	List<Topic> showTopics(String category, int limit, int start);
	List<Message> findAllMessagesByTopicId(Long topicId);
	List<User> getUser(int id);
	boolean ifUserExistInDatabase(String login);
	boolean checkUserInDatabase(User user);
	User findByUsername(String username);
	
}
