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
		model.addAttribute("mess", "Welcome on our computers forum XComp");
		//model.addAttribute("topics", clientService.showTopic("all"));
		model.addAttribute("topics", clientService.getUser(1));
		return "index";
	}
	
	@RequestMapping(value="main")
	public String mainUserPage(Model model){
		model.addAttribute("author", clientService.getUsername());
		return "main";
	}
	
}
