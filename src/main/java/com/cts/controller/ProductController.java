package com.cts.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.cts.model.Product;
import com.cts.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService service;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping(value="/updateproduct")
	public String getproductDetails(@ModelAttribute("product") Product product) {
		
		return "updateproduct";
	}
	
	@GetMapping(value="/categoryselection")
	public String categoryselection(@ModelAttribute("product") Product product,ModelMap map) {
		if(product.getCategory().equals("cooldrinks")) {
			map.put("products",service.getproducts(product));
			return "cooldrinks";
		}
		else if(product.getCategory().equals("dairy")) {
			return "dairy";
		}
		else if(product.getCategory().equals("fruit")) {
			return "fruit";
		}
		else if(product.getCategory().equals("spreads")) {
			return "spreads";
		}
		else if(product.getCategory().equals("frozenfood")) {
			return"frozenfood";
		}
		else {
			return "updateproduct";
		}
	}
	@GetMapping(value="/edit")
	public String editproduct(@ModelAttribute("product")  @Valid Product product,ModelMap map ,String name,BindingResult result) {
		return "editproduct";
		
	}
	@PostMapping(value="/updateEditedForm")
	public String updateEditedForm(@ModelAttribute("product") @Valid Product product,Model map,BindingResult result) {
		if(result.hasErrors()) {
			map.addAttribute("error", "Please Fill all Mandatory fields");
			return "editproduct";
		}
		jdbcTemplate.update("update product set category=?,manufacturer=?,quantity=?,rate=?,discount=? where name=?;",product.getCategory(),product.getManufacturer(),product.getQuantity(),product.getRate(),product.getDiscount(),product.getName());
		return "successfulproduct";
	}
	
	
	
	
	
	@ModelAttribute("categorytypes")
	public List<String> addCategory(){
		List<String> list=new ArrayList<String>();
		list.add("dairy");
		list.add("cooldrinks");
		list.add("spreads");
		list.add("frozenfood");
		list.add("fruit");
		list.add("groceries");
		return list;
	}

}
