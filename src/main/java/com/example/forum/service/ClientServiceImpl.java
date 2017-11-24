package com.example.forum.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.forum.model.Message;
import com.example.forum.model.Topic;
import com.example.forum.model.User;
import com.example.forum.model.UserStat;
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
	public List<Message> findAllByMessagesTopicId(Long topicId, int limit, int start) {
		return clientRepository.findAllMessagesByTopicId(topicId, limit, start);
	}

	@Override
	public String getUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName(); 
		
	}

	@Override
	public User findByUsername(String username) {
		return clientRepository.findByUsername(username);
	}

	@Override
	public int getNextStartValueToShow(int presentStart, int shift, String direction, String category, String choose) {
		int quantityOfRecords;
		
		if(choose.equalsIgnoreCase("topic")){
			quantityOfRecords = clientRepository.getTopiscForCateroryQuantuty(category);
		}
		else{
			quantityOfRecords = getQuantityOfMessagesFromTopic(Long.valueOf(category));
		}
				
		if(direction.equalsIgnoreCase("back")){
			shift = -shift;
		}
	
		System.out.println("IN FUNCTION @@@@@@@@@@@@@@@@@@@ start:"+presentStart
		+" | choose:" + choose + " | quantity:" + quantityOfRecords +" | shift:" +shift+" | cat:"+category );
		
		presentStart += shift;
		if(presentStart < 0){
			presentStart = 0;
		}	
		if(presentStart >= quantityOfRecords){
			presentStart -= shift;
		}
		return presentStart; 
	}

	@Override
	public int getQuantityOfMessagesFromTopic(Long topicId) {
		return clientRepository.getQuantityOfMessagesFromTopic(topicId);
	}

	@Override
	public int giveNumberOfStartingPostAfterAddCommment(Long topicId, int limit) {
		int sizeOfTopic = clientRepository.getQuantityOfMessagesFromTopic(topicId);
		int numberOfPost;
		if(sizeOfTopic % limit != 0){
			numberOfPost = (sizeOfTopic/limit)*limit;
		}
		else{
			numberOfPost = ((sizeOfTopic/limit)*limit) - limit;
		}
		return numberOfPost;
	}

	@Override
	public List<UserStat> getUsersStatistics() {
		return clientRepository.getUsersStatistics();
	}




}
