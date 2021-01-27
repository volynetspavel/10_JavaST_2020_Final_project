package com.volynets.edem.service;

import java.util.List;

import org.testng.annotations.Test;

import com.volynets.edem.entity.User;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.factory.ServiceFactory;

public class TestUserService {
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();
	
	private String surname = "Smith";
	private String name = "Jonh";
	private String avatar = "/img/avatar/no_avatar.png";
	private String email = "user11@gmail.com";
	private String confirmedPassword = "Pass78-word";

	@Test
	public void testRegistration() throws ServiceException {
		userService.registration(surname, name, avatar, email, confirmedPassword);
	}

	@Test
	public void testFindAll() throws ServiceException {
		List<User> users = userService.findAll();
		
		//print users to console
		StringBuilder stringBuilder = new StringBuilder();
		for (User user : users) {
			stringBuilder.append(user.toString()).append("\n");
		}
		System.out.println(stringBuilder.toString());
	}
}
