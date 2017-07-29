package com.example.forum.service;

import java.util.List;

import com.example.forum.model.Topic;
import com.example.forum.model.User;

public interface ClientService {
	List<Topic> showTopic(String category, int limit, int start);
	List<User> getUser(int id);
	boolean registerNewUser(User newUser);
	boolean checkUserInDatabase(User user);
}
