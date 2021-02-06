package com.volynets.edem.controller.command.impl.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.exception.ServiceException;

/**
 * This class is used to visit page for adding new animal.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class VisitAddAnimalCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		return JspPath.ADD_ANIMAL.getUrl();
	}
}
