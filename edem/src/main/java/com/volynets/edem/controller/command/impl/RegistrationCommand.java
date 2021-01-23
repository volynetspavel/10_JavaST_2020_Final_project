package com.volynets.edem.controller.command.impl;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.entity.Account;
import com.volynets.edem.entity.Role;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AccountService;
import com.volynets.edem.service.UserService;
import com.volynets.edem.service.factory.ServiceFactory;
import com.volynets.edem.validator.UserDataValidator;

/**
 * This class is used for user registration.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class RegistrationCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);

	private static final String SURNAME = "surname";
	private static final String NAME = "name";
	private static final String AVATAR = "avatar";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String CONFIRMED_PASSWORD = "confirmedPassword";

	private static final String ERROR_EMAIL = "errorEmail";
	private static final String ERROR_USERNAME = "errorUsername";
	private static final String ERROR_PASS_AND_CONFIRMED_PASS = "errorPassAndConfirmedPassMessage";
	private static final String RESPONSE = "response";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public String executeString(String surname, String name, String avatar, String email, String password,
			String confirmedPassword) throws ServiceException {
		String page;
		boolean fail = false;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		AccountService accountService = serviceFactory.getAccountService();

		UserDataValidator userDataValidator = new UserDataValidator();
		Map<String, String> errorMessages = userDataValidator.validateData(surname, name, avatar, email, password,
				confirmedPassword);

		if (errorMessages.isEmpty()) {
			if (Objects.equals(password, confirmedPassword)) {
				if (userService.containsEmail(email)) {
					errorMessages.put(ERROR_EMAIL, "This email is taken by another account");
				}

				if (errorMessages.isEmpty()) {
					
					userService.registration(surname, name, avatar, email, confirmedPassword);
					
					User user = userService.findByEmailAndPass(email, password).get(0);
					Account account = accountService.findById(user.getId());

					page = "New user. Username = " + account.getSurname() + ", " + account.getName();
				} else {
					fail = true;
					page = errorMessages.toString();
				}
			} else {
				fail = true;
				page = "Password and confirmed password do not match";
			}
		} else {
			fail = true;
			page = errorMessages.toString();
		}

		return page;
	}
}
