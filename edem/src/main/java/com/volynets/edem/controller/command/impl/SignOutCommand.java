package com.volynets.edem.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.ServiceException;

/**
 * This class is used for to sign out.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class SignOutCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(SignOutCommand.class);

	private static final String USER = "user";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		LOGGER.info("User " + ((User) request.getSession().getAttribute(USER)).getEmail() + " signed out.");

		request.getSession().setAttribute(USER, null);

		return JspPath.INDEX.getUrl();
	}
}
