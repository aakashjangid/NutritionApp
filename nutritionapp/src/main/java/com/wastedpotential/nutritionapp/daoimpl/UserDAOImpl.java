package com.wastedpotential.nutritionapp.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wastedpotential.nutritionapp.dao.UserDAO;
import com.wastedpotential.nutritionapp.model.User;
import com.wastedpotential.nutritionapp.rowmapper.UserMapper;

@Repository
public class UserDAOImpl implements UserDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM users";
		try {
			users = jdbcTemplate.query(sql, new UserMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public int registerUser(User user) {
		int success = 0;
		String sql = "INSERT INTO users(name, contact, email, address, password) VALUES(?,?,?,?,?)";
		try {
			success = jdbcTemplate.update(sql, user.getName(), user.getContact(), user.getEmail(), user.getAddress(),
					user.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	public void activateUser(String email) {
		String sql = "UPDATE users SET status=1 WHERE email=?";
		try {
			jdbcTemplate.update(sql, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User authenticateUser(String email, String password) {
		String sql = "SELECT * FROM users WHERE email=? AND password=?";
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] {email, password}, new UserMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}