package com.cts.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cts.model.User;
import com.cts.service.LoginService;

@Controller
public class RegisterController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	LoginService login;
	
	@GetMapping(value="/index")
	public String getregister(@ModelAttribute("user") User user) {
		return "index";
	}
	@GetMapping(value="/abc")
	public String index(@ModelAttribute("user") User user) {
		if(user.getUserCategory().equals("cashier") || user.getUserCategory().equals("manager")) {
			return "login";
		}
		else if(user.getUserCategory().equals("admin")) {
			return "adminlogin";
		}
		return "index";
	}
	
	@GetMapping(value="/login")
	public String getregister1(@ModelAttribute("user") User user) {
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
	
		@PostMapping(value="/postLogin")
       public String postLogin(@ModelAttribute("user") User user,ModelMap map) {
			List<User> list=login.details(user);
			boolean validate1=false,validate2=false;
			for(User i:list) {
				if(i.getUserCategory().equals("manager") || i.getUserCategory().equals("cashier") )
				{
					if(i.getUserId().equals(user.getUserId())) {
					validate1=true;
					break;
				}
				}
			}
				for(User i1:list) {
					if(i1.getPassword().equals(user.getPassword())) {
						validate2=true;
						break;
					}
			}
			if(validate1&&validate2) {
				return "welcome";
			}
			else if(!validate1) {

				map.put("error", "UserId Not Present");
				return "login";
			}
			else if(!validate2) {
				map.put("error", "Password Not Matching");
				return "login";
			}
			else {
			map.put("error", "UserId Not Present");
			return "login";
			}
     }
		@PostMapping(value="/adminLogin")
		public String adminLogin(@ModelAttribute("user") User user,ModelMap map) {
			List<User> list=login.details(user);
			boolean validate1=false,validate2=false;
			for(User i:list) {
				if(i.getUserCategory().equals("admin"))
				{
					if(i.getUserId().equals(user.getUserId())) {
					validate1=true;
					break;
				}
				}
			}
				for(User i1:list) {
					if(i1.getPassword().equals(user.getPassword())) {
						validate2=true;
						break;
					}
			}
			if(validate1&&validate2) {
				return "welcome";
			}
			else if(!validate1) {

				map.put("error", "UserId Not Present");
				return "adminlogin";
			}
			else if(!validate2) {
				map.put("error", "Password Not Matching");
				return "adminlogin";
			}
			else {
			map.put("error", "UserId Not Present");
			return "adminlogin";
			}
		}

}
