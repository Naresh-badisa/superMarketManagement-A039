package com.cts.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cts.model.Cart;
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
	
public List<Cart> getproductbyname(String name) {
        
        List<Cart> cart1=new ArrayList<Cart>();
        
        cart1= jdbcTemplate.query("select * from product where name=?",new RowMapper<Cart>(){

 

            @Override
            public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
                Cart cart1=new Cart();
                cart1.setName(rs.getString("name"));
                cart1.setCategory(rs.getString("category"));
                cart1.setManufacturer(rs.getString("manufacturer"));
                cart1.setQuantity(rs.getDouble("quantity"));
                cart1.setRate(rs.getDouble("rate"));
                cart1.setDiscount(rs.getDouble("discount"));
                return cart1;
            }
            
        },name);
        
        return cart1;
    }
public List<Cart> getcart(Cart cart) {
    
    List<Cart> cart1=new ArrayList<Cart>();
    
    cart1= jdbcTemplate.query("select * from cart where category=?",new RowMapper<Cart>(){



        @Override
        public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cart cart1=new Cart();
            cart1.setName(rs.getString("name"));
            cart1.setCategory(rs.getString("category"));
            cart1.setQuantity(rs.getDouble("quantity"));
            cart1.setRate(rs.getDouble("rate"));
            return cart1;
        }
        
    },cart.getCategory());
    
    return cart1;
}

public double calculatebill() {
	
	double total=0.0;
	 List<Cart> cart1=new ArrayList<Cart>();
	    
	    cart1= jdbcTemplate.query("select * from cart ",new RowMapper<Cart>(){



	        @Override
	        public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Cart cart1=new Cart();
	            cart1.setName(rs.getString("name"));
	            cart1.setCategory(rs.getString("category"));
	            cart1.setQuantity(rs.getDouble("quantity"));
	            cart1.setRate(rs.getDouble("rate"));
	            return cart1;
	        }
	        
	    });
	    
	    for(Cart i:cart1) {
	    	total=total+i.getQuantity()*i.getRate()-(i.getDiscount()*0.01*i.getRate());
	    }
	    
	    return total;
	    
}

}
