package com.example.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.forum.model.User;
import com.example.forum.repository.UserRepository;
import com.example.forum.service.ClientService;

@Controller
public class LoginController {
	
	@Autowired
	ClientService clientService;

	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(Model model){
		model.addAttribute("newUser", new User());
		return "login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String loginPOST(Model model, User newUser){
		   return "main";
	}
}
