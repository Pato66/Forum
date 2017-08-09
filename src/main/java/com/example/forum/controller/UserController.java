package com.example.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.forum.service.ClientService;

@Controller
public class UserController {
	
	@Autowired
	ClientService clientService;
	
	@RequestMapping(value="/userData")
	public String getUserData(Model model){
		model.addAttribute("author", clientService.getUsername());
		return "userData";
	}
	
}
