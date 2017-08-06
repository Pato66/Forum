package com.example.forum.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.forum.service.ClientService;

@Controller
public class TopicController {
	
	@Autowired
	ClientService clientService;

	@Autowired
	TopicRepository topicRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	
	@RequestMapping(value="/addTopic", method=RequestMethod.GET)
	public String readersBooks(Model model) {
		model.addAttribute("newTopic", new Topic());
		return "addTopic";
	}
	
	@RequestMapping(value="/topics", method=RequestMethod.GET)
	public String showTopisc(Model model) {
		model.addAttribute("start",0);
		model.addAttribute("topics", clientService.showTopic("all", 3, 0) );
		return "topics";
	}
	
	@RequestMapping(value="/topics", method=RequestMethod.POST)
	public String showTopiscPOST(Model model, int start ) {
		int limit=3;
		model.addAttribute("start", start += limit);
		model.addAttribute("topics", clientService.showTopic("all", limit, Integer.valueOf(start) ));
		return "topics";
	}
	
	@RequestMapping(value="/addTopic", method=RequestMethod.POST)
	public String addToReadingList( Topic newTopic) {
		newTopic.setDateOfCreation(new Date());
		User user = new User();
		user.setUserId(2);
		newTopic.setUser(user);
		topicRepository.save(newTopic);
		return "redirect:/addTopic";
	}
	
	@RequestMapping(value="/topic", method=RequestMethod.POST)
	public String showTopicDetails(Model model, String  topicId, String description) {
		//model.addAttribute("topic", topicRepository.findOne(Long.valueOf(topicId)) );
		model.addAttribute("messages", clientService.findAllByMessagesTopicId(Long.valueOf(topicId)) );
		model.addAttribute("description", description);
		model.addAttribute("topicId", topicId);
		return "topic";
	}
	
	@RequestMapping(value="/topicAddMessage", method=RequestMethod.POST)
	public String addMessage(Model model, Message message, String  topicId, String description) {
		message.setDateOfPublish(new Date());
		message.setThumbsDown(0);
		message.setThumbsUp(0);
		messageRepository.save(message);
		model.addAttribute("messages", clientService.findAllByMessagesTopicId(Long.valueOf(topicId)) );
		model.addAttribute("description", description);
		model.addAttribute("topicId", topicId);
		return "topic";
	}
	
}
