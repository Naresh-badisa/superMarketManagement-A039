package com.cts.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Service;

import com.cts.model.User;

@Service
public class LoginService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	public List<User> details(User user){
	List<User> user1=new ArrayList<User>();
	
	user1= jdbcTemplate.query("select * from user where userid=?",new RowMapper<User>(){

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user1=new User();
			user1.setUserId(rs.getString("userid"));
			user1.setPassword(rs.getString("password"));
			user1.setFirstName(rs.getString("firstname"));
			user1.setLastName(rs.getString("lastname"));
			user1.setDateOfBirth(rs.getString("dateofbirth"));
			user1.setContactNumber(rs.getString("contactnumber"));
			user1.setUserCategory(rs.getString("usercategory"));
			return user1;
		}
		
	},user.getUserId());
	
	return user1;
	}
}
