package com.volynets.edem.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.entity.Action;
import com.volynets.edem.entity.Animal;
import com.volynets.edem.entity.Role;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.ActionService;
import com.volynets.edem.service.AnimalService;
import com.volynets.edem.service.factory.ServiceFactory;

/**
 * This class is used for view all animals.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class ViewAllAnimalsCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(ViewAllAnimalsCommand.class);

	private static final String ANIMALS = "animals";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		String page;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AnimalService animalService = serviceFactory.getAnimalService();

		List<Animal> animals = animalService.findAll();
		request.setAttribute(ANIMALS, animals);

		page = JspPath.VIEW_ANIMALS.getUrl();

		return page;
	}
}
