package com.volynets.edem.controller.command.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.CommandType;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.entity.Role;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.ServiceException;

/**
 * Defines command for changing language in page.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class LanguageCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(LanguageCommand.class);

	private static final String USER = "user";
	private static final String LOCAL = "local";
	private static final String RESPONSE = "response";
	private static final String COMMAND = "command";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		String language = request.getParameter(LOCAL);
		request.getSession().setAttribute(LOCAL, language);

		String page = request.getHeader("Referer");
		request.setAttribute(RESPONSE, true);

		/*
		 * String previousCommand = (String) request.getSession().getAttribute(COMMAND);
		 * 
		 * if (previousCommand != null &&
		 * CommandType.valueOf(previousCommand.toUpperCase()) == (CommandType.SIGN_IN))
		 * { previousCommand = CommandType.VIEW_ACTIONS.toString(); }
		 * 
		 * if (CommandType.valueOf(previousCommand.toUpperCase()) ==
		 * (CommandType.TAKE_ACTION) ||
		 * CommandType.valueOf(previousCommand.toUpperCase()) ==
		 * (CommandType.ADD_COMMENT)) { previousCommand =
		 * CommandType.WATCH_ACTION.toString(); }
		 * 
		 * if (CommandType.valueOf(previousCommand.toUpperCase()) ==
		 * (CommandType.TAKE_ANIMAL)) { previousCommand =
		 * CommandType.VIEW_ANIMALS.toString(); }
		 * 
		 * if (CommandType.valueOf(previousCommand.toUpperCase()) ==
		 * (CommandType.SIGN_OUT)) { previousCommand = null; }
		 * 
		 * if (!page.contains("?")) { page = page + "?command=" + previousCommand; }
		 * else { page = page + "&command=" + previousCommand; }
		 */

		LOGGER.info("Language changed into " + language);
		request.setAttribute(RESPONSE, true);

		String previousCommand = (String) request.getSession().getAttribute(COMMAND);
		request.setAttribute(COMMAND, previousCommand);

		/*
		 * // if (!page.contains("?")) { // page = page + "?command=" + previousCommand;
		 * // } else { // page = page + "&command=" + previousCommand; // }
		 */
		LOGGER.info("Language changed into " + language);
		return page;
	}

}
