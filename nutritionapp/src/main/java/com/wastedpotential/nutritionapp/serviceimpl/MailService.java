package com.wastedpotential.nutritionapp.serviceimpl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

/**
 * This class will be responsible to send email to the customers on their
 * successful registration.
 * 
 * @version - 1.1
 * @since 06 April, 2018
 * @Service annotation is used in your service layer and annotates classes that
 *          perform service tasks
 * @author aakash.jangid
 *
 */

@Service
public class MailService {

	/**
	 * This is the sender's Email Address through which the email will be send. This
	 * email address is also used to authenticate the Session which will be used in
	 * MIME Message.
	 */
	private String senderAddress = "yashcms2018@gmail.com";

	/**
	 * This is the sender's Password through which the email will be send. This
	 * password is also used to authenticate the Session which will be used in MIME
	 * Message.
	 */
	private final String password = "admin@yashcms";

	public String getSenderAddress() {
		return senderAddress;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * This method will send an email to the recipient email address passed as an
	 * argument. It will inform the registered user about the successful
	 * registration with the application. It will also provide login email and
	 * domain name in the body of the email. It will also provide a link of the
	 * login page for the registered user.
	 * 
	 * @author aakash.jangid
	 * 
	 * @param recipientAddress
	 *            - the address on which the email has to send.
	 * @param domain
	 *            - the domain name for which the user has registered.
	 * @param loginURL
	 *            - the URL through which the user will be able to redirect to login
	 *            page.
	 * @return true if email is sent successfully or false if email is not send
	 * @exception java.lang.AssertionError
	 *                It will throw AssertionError if the credentials are incorrect.
	 *                Also if the properties are incorrect.
	 */
	public boolean sendEmail(String recipientAddress, String loginURL) {

		Session session = getMailSession();
		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(senderAddress));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientAddress));

			message.setSubject("Nutrition App : Registration Successful.");

			message.setContent("<h1 style='color:green'>Thank You for Registering at Nutrition Application</h1>"
					+ "<img src=\"https://static1.squarespace.com/static/54c0117fe4b09474f2e4d227/t/58ea60a02e69cf2772c20a06/1491755178481\">"
					+ "<br/>"
					+ "<p><strong>Nutrition App</strong> is an application that help its user with the proper guide about Nutritions as well as Diet and many more things.</p>"
					+ "<p>If you will follow our instructions and tips then you can observe the results within 15-20days.</p>"
					+ "<br/>" + "<p>You have registered using following credentials - </p>" + "<br/>"
					+ "<h4 style='color:blue'>Username : " + recipientAddress + "</h4>"
					+ "<p>To activate your account please Click on the below link.</p>" + "<a href=" + loginURL + "> "
					+ loginURL
					+ "</a><br/><br/> <p>(<strong>NOTE : </strong>In case if persist any problem, kindly copy and paste this link in your browser. )</p>",
					"text/html");

			Transport.send(message);
			return true;

		} catch (MessagingException e) {
			return false;
		}
	}

	/**
	 * This method will be used to get the Session object. It will take the
	 * properties of the email server and it will authenticate the email account
	 * with the help of email address and password. Properties are -
	 * Host,Port,Authentication,STARTTLS
	 * 
	 * @return javax.mail.Session - It will return the Session Object if the
	 *         properties and email and password are correct. - It will return null
	 *         if the properties and email and password are incorrect.
	 * 
	 * @exception java.lang.AssertionError
	 *                It will throw AssertionError if the credentials are incorrect.
	 *                Also if the properties are incorrect.
	 * 
	 * @author aakash.jangid
	 */
	public Session getMailSession() {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		return Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(getSenderAddress(), getPassword());
			}
		});
	}

}