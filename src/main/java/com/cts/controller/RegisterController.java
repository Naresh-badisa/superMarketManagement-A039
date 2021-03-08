package com.cts.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cts.model.User;

@Controller
public class RegisterController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping(value="/index")
	public String getregister(@ModelAttribute("user") User user) {
		return "index";
	}
	
	@GetMapping(value="/login")
	public String getregister1() {
		return "login";
	}
	@GetMapping(value="register")
	public String register(@ModelAttribute("user") @Valid User user,BindingResult result) {
		return "register";
	}
	
	@PostMapping(value="/registration")
	public String register1(@ModelAttribute("user") @Valid User user,BindingResult result,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("error","Please update highlighted mandatory field");
			return "register";
		}
		jdbcTemplate.update("insert into user values(?,?,?,?,?,?,?,?,?)",user.getFirstName(),user.getLastName(),user.getDateOfBirth(),user.getGender(),user.getContactNumber(),user.getEmail(),user.getUserId(),user.getPassword(),user.getUserCategory());
		return "welcome";
	}
	



}
