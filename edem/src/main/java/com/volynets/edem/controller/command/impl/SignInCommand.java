package com.volynets.edem.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.entity.Account;
import com.volynets.edem.entity.Role;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AccountService;
import com.volynets.edem.service.UserService;
import com.volynets.edem.service.factory.ServiceFactory;
import com.volynets.edem.service.impl.UserServiceImpl;

/**
 * This class is used for to sign in.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class SignInCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(SignInCommand.class);

	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String USER = "user";
	private static final String ERROR_INPUT_DATA = "errorInputData";
	private static final String ERROR = "error";
	private static final String STATUS_CODE = "statusCode";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		String page;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		AccountService accountService = serviceFactory.getAccountService();

		String email = request.getParameter(EMAIL);
		String password = request.getParameter(PASSWORD);
		
		if (email != null && password != null) {
			User user = null;
			Account account = null;

			if (!userService.findByEmailAndPass(email, password).isEmpty()) {

				user = userService.findByEmailAndPass(email, password).get(0);
				account = accountService.findById(user.getId());
			}

			if (user != null) {
				if (user.getRole() == Role.USER) {
					LOGGER.info("User " + account.getSurname() + " with email " + user.getEmail() + " signed in.");

					request.getSession().setAttribute(USER, user);
					request.setAttribute(USER, account.getName());
					page = JspPath.HOME_USER.getUrl();
				} else {
					LOGGER.info("User " + account.getSurname() + " with email " + user.getEmail() + " signed in.");

					request.getSession().setAttribute(USER, user);
					request.setAttribute(USER, account.getName());
					page = JspPath.HOME_ADMIN.getUrl();
				}
			} else {
				LOGGER.error("SignInCommand. Incorrect username or email or password");
				request.setAttribute(ERROR_INPUT_DATA, "Incorrect email or password");
				request.setAttribute(EMAIL, email);
				page = JspPath.SIGN_IN.getUrl();
			}
		} else {
			LOGGER.error("SignInCommand. Error request");
			request.setAttribute(ERROR, "Error request");
			request.setAttribute(STATUS_CODE, 404);
			page = JspPath.ERROR.getUrl();
		}
		return page;
	}
}
