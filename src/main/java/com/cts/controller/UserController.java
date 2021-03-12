package com.cts.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cts.model.User;
import com.cts.service.LoginService;
import com.cts.service.ViewAllUsers;

@Controller
public class UserController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	LoginService login;

	@GetMapping(value = "/index")
	public String getregister(@ModelAttribute("user") User user) {
		return "index";
	}

	@GetMapping(value = "/abc")
	public String index(@ModelAttribute("user") User user) {
		if (user.getUserCategory().equals("cashier") || user.getUserCategory().equals("manager")) {
			return "login";
		} else if (user.getUserCategory().equals("admin")) {
			return "adminlogin";
		}
		return "index";
	}

	@GetMapping(value = "/login")
	public String getregister1(@ModelAttribute("user") User user) {
		return "login";
	}

	@GetMapping(value = "register")
	public String register(@ModelAttribute("user") @Valid User user, BindingResult result) {
		return "register";
	}

	@PostMapping(value = "/registration")
	public String register1(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("error", "Please update highlighted mandatory field");
			return "register";
		}
		List<User> list = login.details(user);
		boolean exist=false;
		for(User i:list) {
			if(user.getUserId().equals(i.getUserId())) {
				exist=true;
				break;
			}
		}
		if(exist) {
			model.addAttribute("error", "UserId already exist");
			return "register";
		}
		jdbcTemplate.update("insert into user values(?,?,?,?,?,?,?,?,?,?)", user.getFirstName(), user.getLastName(),
				user.getDateOfBirth(), user.getGender(), user.getContactNumber(), user.getEmail(), user.getUserId(),
				user.getPassword(), user.getUserCategory(), user.getStatus());
		return "welcome";
	}

	@PostMapping(value = "/postLogin")
	public String postLogin(@ModelAttribute("user") User user, ModelMap map) {
		List<User> list = login.details(user);
		boolean validate1 = false, validate2 = false, validate3 = false, validate4 = false;
		String name = null, user2 = null;
		for (User i : list) {
			if(i.getStatus().equals("active")) {
			if (i.getUserCategory().equals("manager")) {
				if (i.getUserId().equals(user.getUserId())) {
					validate1 = true;
					name = i.getFirstName();
					user2 = i.getUserCategory();
					break;
				}
			}
			if (i.getUserCategory().equals("cashier")) {
				if (i.getUserId().equals(user.getUserId())) {
					validate3 = true;
					name = i.getFirstName();
					user2 = i.getUserCategory();
					break;
				}
			}
			}
		}
		for (User i1 : list) {
			if (i1.getUserCategory().equals("manager")) {
				if (i1.getPassword().equals(user.getPassword())) {
					validate2 = true;
					break;
				}
			}
			if (i1.getUserCategory().equals("cashier")) {
				if (i1.getUserId().equals(user.getUserId())) {
					validate4 = true;
					name = i1.getFirstName();
					break;
				}
			}

		}

		if (validate1 && validate2) {
			map.put("name", name);
			return "welcomemanager";
		} else if (validate3 && validate4) {
			map.put("name", name);
			return "welcomecashier";
		}

		else if (!validate1) {
			map.put("error", "UserId Not Present");
			return "login";
		} else if (!validate2) {

			map.put("error", "Password Not Matching");
			return "login";
		}

		else if (!validate3) {
			map.put("error", "UserId Not Present");
			return "login";
		} else if (!validate4) {

			map.put("error", "Password Not Matching");
			return "login";
		}

		else {
			map.put("error", "UserId Not Present");
			return "login";
		}
	}

	@PostMapping(value = "/adminLogin")
	public String adminLogin(@ModelAttribute("user") User user, ModelMap map) {
		List<User> list = login.details(user);
		boolean validate1 = false, validate2 = false;
		String name = null;
		for (User i : list) {
			if (i.getUserCategory().equals("admin")) {
				if (i.getUserId().equals(user.getUserId())) {
					validate1 = true;
					name = i.getFirstName();
					break;
				}
			}
		}
		for (User i1 : list) {
			if (i1.getPassword().equals(user.getPassword())) {
				validate2 = true;
				break;
			}
		}
		if (validate1 && validate2) {
			map.put("name", name);
			return "welcomeadmin";
		}

		else if (!validate1) {
			map.put("error", "UserId Not Present");
			return "adminlogin";
		} else if (!validate2) {
			map.put("error", "Password Not Matching");
			return "adminlogin";
		} else {
			map.put("error", "UserId Not Present");
			return "adminlogin";
		}

	}

	@GetMapping(value = "/viewregistrations")
	public String viewRegistration(@ModelAttribute("user") User user, ModelMap map) {
		map.put("list", login.details(user));

		return "viewregistration";
	}
	@GetMapping(value = "/update")
	public String approval(String userid) {
		jdbcTemplate.update("update user set status=? where userid=?","active",userid);
		return "welcome1";
	}

	@GetMapping(value = "/delete")
	public String delete(String userid) {
		jdbcTemplate.execute("delete from user where userid="+userid);
		return "viewregistration";
	}

	@GetMapping(value = "/logoff")
	public String logoff() {
		return "logoff";
	}

	@ModelAttribute("statusList")
	public List<String> listofStatus() {
		List<String> list = new ArrayList<String>();
		list.add("accept");
		list.add("decline");
		return list;
	}

}
