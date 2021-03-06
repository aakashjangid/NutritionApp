package com.wastedpotential.nutritionapp.dao;

import java.util.List;

import com.wastedpotential.nutritionapp.model.User;

public interface UserDAO {

	public List<User> getAllUsers();
	
	public int registerUser(User user);

	public void activateUser(String email);

	public User authenticateUser(String email, String password);
	
}
