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
		if(product.getCategory().equals("beverages")) {
			map.put("products",service.getproducts(product));
			return "beverages";
		}
		else if(product.getCategory().equals("dairy")) {
			return "dairy";
		}
		else if(product.getCategory().equals("produce")) {
			return "produce";
		}
		else if(product.getCategory().equals("bakery")) {
			return "bakery";
		}
		else if(product.getCategory().equals("frozenfood")) {
			return"frozenfood";
		}
		else if(product.getCategory().equals("meat")) {
			return "meat";
		}
		else if(product.getCategory().equals("cleaners")) {
			return "cleaners";
		}
		else if(product.getCategory().equals("papergoods")) {
			return "papergoods";
		}
		else if(product.getCategory().equals("personalcare")) {
			return "personalcare";
		}
		else if(product.getCategory().equals("others")) {
			return "others";
		}
		else {
			return "updateproduct";
		}
	}
	@GetMapping(value="/edit")
	public String editproduct(@ModelAttribute("product")  @Valid Product product,ModelMap map ,String name,BindingResult result) {
		return "editproduct";
		
	}
	@GetMapping(value="/deleteproduct")
	public String deleteproduct(String name) {
		
		jdbcTemplate.update("delete from product where name=?",name);
		return "successfulproduct";
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
	
	@GetMapping(value="/addproduct")
	public String additem(@ModelAttribute("product")  Product product,Model map) {
		return"addproduct";
	}
		
	@PostMapping(value="/addproductdb")
	public String additemdb(@ModelAttribute("product")  Product product,Model map) {
		jdbcTemplate.update("insert into product values(?,?,?,?,?,?)",product.getName(),product.getCategory(),product.getManufacturer(),product.getQuantity(),product.getRate(),product.getDiscount());
		return "successfulproduct";
	}
	
	
	@ModelAttribute("categorytypes")
	public List<String> addCategory(){
		List<String> list=new ArrayList<String>();
		list.add("dairy");
		list.add("beverages");
		list.add("bakery");
		list.add("frozenfood");
		list.add("produce");
		list.add("meat");
		list.add("cleaners");
		list.add("papergoods");
		list.add("groceries");
		list.add("personalcare");
		list.add("others");
		return list;
	}

}
