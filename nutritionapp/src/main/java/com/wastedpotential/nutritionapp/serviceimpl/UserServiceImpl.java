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
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private AESEncryption aesEncryption;

	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	public int register(User user) {
		int success = userDAO.registerUser(user);
		if (success == 1) {
			WaySms.send(user.getContact(),
					"Dear " + user.getName() + ", Your registration has been successful on Nutrition App with Email - "
							+ user.getEmail() + " .We have sent an activation link your email.");
			try {
				byte[] cy1 = aesEncryption.encryptText(user.getEmail());
				String encryptedCode = aesEncryption.bytesToHex(cy1);
				mailService.sendEmail(user.getEmail(), "http://yi1007106dt:8080/nutritionapp/users/register/"+encryptedCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	public String getEmail(String encrypt) {
		byte[] code = aesEncryption.hexToBytes(encrypt);
		try {
			return aesEncryption.decryptText(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void activateUser(String email) {
		userDAO.activateUser(email);
	}

}