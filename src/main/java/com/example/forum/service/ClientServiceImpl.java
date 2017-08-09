package com.example.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.forum.model.Message;
import com.example.forum.model.Topic;
import com.example.forum.model.User;
import com.example.forum.repository.ClientRepository;
import com.example.forum.repository.UserRepository;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	ClientRepository clientRepository;

	@Override
	public List<Topic> showTopic(String category, int limit, int start) {
		return clientRepository.showTopics(category, limit, start);
	}

	@Override
	public List<User> getUser(int id) {
		return clientRepository.getUser(id);
	}

	@Override
	public boolean registerNewUser(User newUser) {
		if(clientRepository.ifUserExistInDatabase(newUser.getLogin())){
			return false;
		}
		else{
			newUser.setCity("fill in");
			newUser.setEmail("fill in");
			newUser.setFirstName("fill in");
			newUser.setLastName("fill in");
			userRepository.save(newUser);
			return true;
		}
	}

	@Override
	public boolean checkUserInDatabase(User user) {
		return clientRepository.checkUserInDatabase(user);
	}

	@Override
	public List<Message> findAllByMessagesTopicId(Long topicId) {
		return clientRepository.findAllMessagesByTopicId(topicId);
	}

	@Override
	public String getUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName(); 
		
	}


}
