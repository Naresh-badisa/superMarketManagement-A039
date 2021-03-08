package com.cts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cts.model.User;

@Controller
public class RegisterController {
	
	@GetMapping(value="/index")
	public String getregister() {
		return "index";
	}
	
	@GetMapping(value="/login")
	public String getregister1() {
		return "login";
	}
	@GetMapping(value="register")
	public String register(@ModelAttribute("user") User user) {
		return "register";
	}
	
	@PostMapping(value="/registration")
	public String register1(@ModelAttribute("user") User user) {
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		return "register";
	}
	



}
