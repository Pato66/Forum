package com.example.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.forum.model.User;
import com.example.forum.repository.UserRepository;

@Controller
public class AdminController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="admin", method=RequestMethod.GET)
	public String registaration(Model model){
		return "admin";
	}
	
	@RequestMapping(value="403", method=RequestMethod.GET)
	public String error403(Model model){
		return "403";
	}
	
	@RequestMapping(value="users", method=RequestMethod.GET)
	public String showUsers(Model model){
		model.addAttribute("users", userRepository.findAll());
		return "users";
	}
	
}
