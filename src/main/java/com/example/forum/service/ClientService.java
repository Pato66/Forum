package com.example.forum.service;

import java.util.List;

import com.example.forum.model.Message;
import com.example.forum.model.UserStat;
import com.example.forum.model.Topic;
import com.example.forum.model.User;

public interface ClientService {
	
	List<Topic> showTopic(String category, int limit, int start);
	
	List<User> getUser(int id);
	
	boolean registerNewUser(User newUser);
	
	boolean checkUserInDatabase(User user);
	
	List<Message> findAllByMessagesTopicId(Long topicId, int limit, int start);
	
	int getQuantityOfMessagesFromTopic(Long topicId);
	
	String getUsername();
	
	User findByUsername(String username); 
	
	int getNextStartValueToShow(int presentStart, int shift, String direction, String category, String choose);
	
	int giveNumberOfStartingPostAfterAddCommment(Long topicId, int limit);

	List<UserStat> getUsersStatistics();
	
}
