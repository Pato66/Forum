package com.example.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.forum.model.User;
import com.example.forum.repository.UserRepository;
import com.example.forum.service.ClientService;

@Controller
public class RegisterController {
	
	@Autowired
	ClientService clientService;
	
	private UserRepository userRepository;
	
	@Autowired
	public RegisterController(
		UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value="registration", method=RequestMethod.GET)
	public String registaration(Model model){
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@RequestMapping(value="registration", method=RequestMethod.POST)
	public String registarationPOST(Model model,@Valid User user, BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
			if(!clientService.registerNewUser(user)){
				bindingResult.rejectValue("login", "login", "This login is already taken");
			}
		    return "registration";
		} 
		
		if(clientService.registerNewUser(user)){
			return "redirect:/login";
		}
		else{
			bindingResult.rejectValue("login", "login", "This login is already taken");
			return "redirect:/registration";
		}
	}
}
