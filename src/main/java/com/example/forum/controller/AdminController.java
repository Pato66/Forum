package com.example.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.forum.model.User;

@Controller
public class AdminController {
	
	@RequestMapping(value="admin", method=RequestMethod.GET)
	public String registaration(Model model){
		return "admin";
	}
	
}
