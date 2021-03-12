package com.cts.service;
import java.util.ArrayList;
import java.util.List;
import com.cts.model.User;

public class ViewAllUsers {
	
	public List<User> allUsers(User user){
	List<User> user1=new ArrayList<User>();
	user1.add(user);
	return user1;

}
}
