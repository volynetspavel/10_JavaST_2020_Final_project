package com.volynets.edem.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.exception.ServiceException;

/**
 * Defines command for changing language in page.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class LanguageCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(LanguageCommand.class);
	private static final String LOCAL = "local";
    private static final String RESPONSE = "response";
	private static final String COMMAND = "command";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		String language = request.getParameter(LOCAL);
		request.getSession().setAttribute(LOCAL, language);
		
		String page = request.getHeader("Referer");
		
		LOGGER.info("Language changed into " + language);
        request.setAttribute(RESPONSE, true);
        
        String previousCommand = (String) request.getSession().getAttribute(COMMAND);
        request.setAttribute(COMMAND, previousCommand);
        
        if (!page.contains("?")) {
            page = page + "?command=" + previousCommand;
		}
		return page;
	}

}
