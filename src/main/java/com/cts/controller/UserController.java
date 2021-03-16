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

import com.cts.model.Cart;
import com.cts.model.Customer;
import com.cts.model.Product;
import com.cts.model.User;
import com.cts.service.LoginService;
import com.cts.service.ProductService;
import com.cts.service.ViewAllUsers;

@Controller
public class UserController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	LoginService login;
	@Autowired
	ProductService service;

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
		boolean exist = false;
		for (User i : list) {
			if (user.getUserId().equals(i.getUserId())) {
				exist = true;
				break;
			}
		}
		if (exist) {
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
			if (i.getStatus().equals("active")) {
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
				if (i1.getPassword().equals(user.getPassword())) {
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

	@GetMapping(value = "/addcustomer")
	public String addcustomer(@ModelAttribute("customer") Customer customer) {
		return "customerRegistrationForm";
	}

	@PostMapping(value = "/customerRegistration")
	public String customerRegistration(@ModelAttribute("customer") @Valid Customer customer, BindingResult result,
			ModelMap map) {
		if (result.hasErrors()) {
			map.put("error", "Please update the details in highlighted fields");
			return "customerRegistrationForm";
		}

		jdbcTemplate.update("insert into customer(firstname,lastname,dateofbirth,gender,contactnumber,email) values(?,?,?,?,?,?)", customer.getFirstName(), customer.getLastName(),
				customer.getDateOfBirth(), customer.getGender(), customer.getContactNumber(), customer.getEmail());
		return "welcome";
	}

	@GetMapping(value = "/generatebill")
	public String generateBill(@ModelAttribute Cart cart) {
		return "billgeneration";
	}

	@GetMapping(value = "/billgeneration")
	public String billgeneration(@ModelAttribute("product") Product product, ModelMap map) {
		if (product.getCategory().equals("beverages")) {
			return "beveragesbill";
		} else if (product.getCategory().equals("dairy")) {
			return "dairybill";
		} else if (product.getCategory().equals("produce")) {
			return "producebill";
		} else if (product.getCategory().equals("bakery")) {
			return "bakerybill";
		}else if(product.getCategory().equals("cleaners")) {
			return "cleanersbill";
		}else if(product.getCategory().equals("meat")) {
			return "meatbill";
		}else if(product.getCategory().equals("groceries")) {
			return "groceriesbill";
		}else if(product.getCategory().equals("papergoods")) {
			return "papergoodsbill";
		}else if(product.getCategory().equals("personalcare")) {
			return "personalcarebill";
		}else if(product.getCategory().equals("frozenfood")) {
			return "frozenfoodbill";
		}
		else if(product.getCategory().equals("others")) {
			return "othersbill";
		}
		
		else {
			return "billgeneration";
		}
	}

	@GetMapping(value = "/addtocart")
	public String getdairybill(@ModelAttribute("cart")Cart cart, String name) {
		double reduce=0.0;
		List<Cart> list = service.getproductbyname(name);
		System.out.println("hello world "+list.size()+name);
		for (Cart i : list) {
			System.out.println(
					i.getName() + i.getCategory() + i.getManufacturer() +i.getQuantity()+ i.getDiscount() + i.getRate() );
			reduce=i.getQuantity();
			if(reduce==0) {
				return "itemNotPresent";
			}
			jdbcTemplate.update("insert into cart values(?,?,?,?,?,?)", i.getName(), i.getCategory(),i.getManufacturer(),
				1.0,i.getRate(),i.getDiscount());
			reduce=i.getQuantity()-1.0;
			jdbcTemplate.update("update product set quantity=? where name=?",reduce,i.getName());
		}
		return "billgeneration";
	}

	@GetMapping(value = "/cartlist")
	public String cartlist(@ModelAttribute("cart") Cart cart, ModelMap map) {
		map.put("cart", service.getcart(cart));
		return "cartlist";
	}
	@GetMapping(value="/calculatebill")
	public String calculatebill(@ModelAttribute("cart") Cart cart, ModelMap map) {
		map.put("total", service.calculatebill());
		map.put("customerid", service.getcustomerid());
		return "showbill";
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
		jdbcTemplate.update("update user set status=? where userid=?", "active", userid);
		return "welcome1";
	}

	@GetMapping(value = "/delete")
	public String delete(String userid) {
		jdbcTemplate.execute("delete from user where userid=" + userid);
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
