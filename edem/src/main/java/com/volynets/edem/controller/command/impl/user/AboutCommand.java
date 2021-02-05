package com.volynets.edem.controller.command.impl.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.exception.ServiceException;

/**
 * Defines command for view information about app.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class AboutCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		return JspPath.ABOUT.getUrl();
	}

}
