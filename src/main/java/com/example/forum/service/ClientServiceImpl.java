package com.example.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forum.model.Topic;
import com.example.forum.model.User;
import com.example.forum.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	ClientRepository clientRepository;

	@Override
	public List<Topic> showTopic(String category) {
		return clientRepository.showTopics(category);
	}

	@Override
	public List<User> getUser(int id) {
		return clientRepository.getUser(id);
	}
	
}
