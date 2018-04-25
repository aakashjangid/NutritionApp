package com.wastedpotential.nutritionapp.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wastedpotential.nutritionapp.model.User;

public class UserMapper implements RowMapper<User>{

	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setContact(rs.getString("contact"));
		user.setEmail(rs.getString("email"));
		user.setAddress(rs.getString("address"));
		user.setPassword(rs.getString("password"));
		user.setStatus(rs.getInt("status"));
		return user;
	}

}