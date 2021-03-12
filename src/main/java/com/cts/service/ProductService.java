package com.cts.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cts.model.Product;
import com.cts.model.User;
@Service
public class ProductService {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Product> getproducts(Product product) {
		
		List<Product> product1=new ArrayList<Product>();
		
		product1= jdbcTemplate.query("select * from product where category=?",new RowMapper<Product>(){

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product1=new Product();
				product1.setName(rs.getString("name"));
				product1.setCategory(rs.getString("category"));
				product1.setManufacturer(rs.getString("manufacturer"));
				product1.setQuantity(rs.getDouble("quantity"));
				product1.setRate(rs.getDouble("rate"));
				product1.setDiscount(rs.getDouble("discount"));
				return product;
			}
			
		},product.getCategory());
		
		return product1;
	}
}
