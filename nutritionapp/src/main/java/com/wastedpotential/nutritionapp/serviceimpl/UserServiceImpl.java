package com.wastedpotential.nutritionapp.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wastedpotential.nutritionapp.dao.UserDAO;
import com.wastedpotential.nutritionapp.model.User;
import com.wastedpotential.nutritionapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	public int register(User user) {
		int success = userDAO.registerUser(user);
		if (success == 1) {
			WaySms.send(user.getContact(),
					"Dear " + user.getName() + ", Your registration has been successful on Nutrition App with Email - "
							+ user.getEmail() + " .");
		}
		return success;
	}

}