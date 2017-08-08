package com.example.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.forum.service.ClientService;

@Controller
public class MainController {
	
	@Autowired
	ClientService clientService;
	
	@RequestMapping(value="index")
	public String index(Model model){
		model.addAttribute("mess", "Main page");
		//model.addAttribute("topics", clientService.showTopic("all"));
		model.addAttribute("topics", clientService.getUser(1));
		return "index";
	}
	
	@RequestMapping(value="main")
	public String mainUserPage(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
		model.addAttribute("message", "Welcome To Login Form Based Spring Security Example!!!");
	    model.addAttribute("author", name);
		return "main";
	}
	
	@RequestMapping(value="/userData")
	public String getUserData(){
		return "userData";
	}
	
}
