package com.example.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.forum.model.User;
import com.example.forum.repository.ClientRepository;
import com.example.forum.repository.UserRepository;
import com.example.forum.service.ClientService;

@Controller
public class UserController {
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClientRepository clientrepository;
	
	@RequestMapping(value="/userData", method=RequestMethod.GET)
	public String getUserData(Model model){
		String name = clientService.getUsername();
		model.addAttribute("author", name);
		model.addAttribute("user", clientrepository.findByUsername(name));
		return "userData";
	}
	
	@RequestMapping(value="/userData", method=RequestMethod.POST)
	public String serUserData(Model model, User user){
		
		userRepository.save(user);
		
		String name = clientService.getUsername();
		model.addAttribute("author", name);
		model.addAttribute("user", clientrepository.findByUsername(name));
		return "userData";
	}
	
}
