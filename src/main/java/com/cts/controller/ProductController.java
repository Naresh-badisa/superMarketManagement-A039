package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cts.model.Product;
import com.cts.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService service;
	
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
		else {
			return "updateproduct";
		}
	}

}
