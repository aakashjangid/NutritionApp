package com.wastedpotential.nutritionapp.service;

import java.util.List;

import com.wastedpotential.nutritionapp.model.User;

public interface UserService {

	public List<User> getAllUsers();

	public int register(User user);

	public String getEmail(String encrypt);

	public void activateUser(String email);

	public User authenticateUser(String email, String password);
	
}