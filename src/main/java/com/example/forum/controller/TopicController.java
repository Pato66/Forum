package com.example.forum.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.forum.model.Message;
import com.example.forum.model.Topic;
import com.example.forum.model.User;
import com.example.forum.repository.MessageRepository;
import com.example.forum.repository.TopicRepository;
import com.example.forum.repository.UserRepository;
import com.example.forum.service.ClientService;

@Controller
public class TopicController {
	
	@Autowired
	ClientService clientService;

	@Autowired
	TopicRepository topicRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	UserRepository userRepository;
	
	private int limit = 5;
	
	@RequestMapping(value="/addTopic", method=RequestMethod.GET)
	public String readersBooks(Model model) {
		model.addAttribute("newTopic", new Topic());
		model.addAttribute("author", clientService.getUsername());
		return "addTopic";
	}
	//-----------------------------------------------------------------------------------
	@RequestMapping(value="/topics", method=RequestMethod.GET)
	public String showTopisc(Model model) {
		model.addAttribute("phrase","");
		model.addAttribute("start",0);
		model.addAttribute("topics", clientService.showTopic("",limit, 0) );
		model.addAttribute("author", clientService.getUsername());
		return "topics";
	}
	//------------------------------------------------------------------------------------
	@RequestMapping(value="/topics", method=RequestMethod.POST)
	public String showTopiscPOST(Model model, int start, String direction, String phrase ) {
		
		start = clientService.getNextStartValueToShow(start, limit, direction, phrase, "topic");
		model.addAttribute("phrase",phrase);
		model.addAttribute("start", start);
		model.addAttribute("topics", clientService.showTopic(phrase, limit, Integer.valueOf(start) ));
		model.addAttribute("author", clientService.getUsername());
		return "topics";
	}
	//------------------------------------------------------------------------------------
	@RequestMapping(value="/topicsSearch", method=RequestMethod.POST)
	public String showTopiscPOSTSearch(Model model, int start, String direction, String phrase ) {
		model.addAttribute("phrase",phrase);
		model.addAttribute("start", 0);
		model.addAttribute("topics", clientService.showTopic(phrase, limit, Integer.valueOf(start) ));
		model.addAttribute("author", clientService.getUsername());
		return "topics";
	}
	//-------------------------------------------------------------------------------------
	@RequestMapping(value="/addTopic", method=RequestMethod.POST)
	public String addToReadingList( Topic newTopic, Model model, String direction) {
		newTopic.setDateOfCreation(new Date());
		User user = new User();
		String username  = clientService.getUsername();
		user.setUserId(clientService.findByUsername(username).getUserId());
		newTopic.setUser(user);
		topicRepository.save(newTopic);
		int start = 0;
		model.addAttribute("start", start);
		model.addAttribute("topics", clientService.showTopic("all", limit, Integer.valueOf(start) ));
		model.addAttribute("author", clientService.getUsername());
		
		return "main";
	}
	//----------------------------------------------------------------------------------------
	@RequestMapping(value="/topic", method=RequestMethod.POST)
	public String showTopicDetails(Model model, String  topicId, String description) {
		String username  = clientService.getUsername();
		model.addAttribute("start",0);
		model.addAttribute("messages", clientService.findAllByMessagesTopicId(Long.valueOf(topicId), limit, 0 ) );
		model.addAttribute("description", description);
		model.addAttribute("topicId", topicId);
		model.addAttribute("author", username);
		model.addAttribute("authorId",clientService.findByUsername(username).getUserId());
		
		model.addAttribute("users", userRepository.findAll());
		
		return "topic";
	}
	//----------------------------------------------------------------------------------------------
	@RequestMapping(value="/topicChangePage", method=RequestMethod.POST)
	public String showTopicDetailsChangePage(Model model, String  topicId, 
			String description, int start, String direction) {
		
		String username  = clientService.getUsername();
		Long authorId = clientService.findByUsername(username).getUserId();
		start = clientService.getNextStartValueToShow(start, limit, direction, String.valueOf(topicId) , "message");
		model.addAttribute("start", start);
		
		model.addAttribute("start",start);
		model.addAttribute("messages", clientService.findAllByMessagesTopicId
														( Long.valueOf(topicId), limit, Integer.valueOf(start) ) );
		model.addAttribute("description", description);
		model.addAttribute("topicId", topicId);
		model.addAttribute("author", username);
		model.addAttribute("authorId", authorId);
		return "topic";
	}
	//----------------------------------------------------------------------------------------------------
	@RequestMapping(value="/topicAddMessage", method=RequestMethod.POST)
	public String addMessage(Model model, Message message, String  topicId, String description) {
		message.setDateOfPublish(new Date());
		message.setThumbsDown(0);
		message.setThumbsUp(0);
		messageRepository.save(message);
		String username  = clientService.getUsername();
		
		model.addAttribute("start", clientService.giveNumberOfStartingPostAfterAddCommment(Long.valueOf(topicId), limit));
		model.addAttribute("messages", clientService.findAllByMessagesTopicId(Long.valueOf(topicId), 5, 0 ) );
		model.addAttribute("description", description);
		model.addAttribute("topicId", topicId);
		model.addAttribute("author", username);
		model.addAttribute("authorId",clientService.findByUsername(username).getUserId());
		return "topic";
	}
	
}
