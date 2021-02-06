package com.volynets.edem.controller.command.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.entity.Animal;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AnimalService;
import com.volynets.edem.service.factory.ServiceFactory;
import com.volynets.edem.tag.Pagination;

/**
 * This class is used for view all animals.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class ViewAllAnimalsCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(ViewAllAnimalsCommand.class);

	private static final String ANIMALS = "animals";
	private static final String PAGINATION = "pagination";
	private static final String PAGE = "page";
	private static final int LIMIT_ARTICLES_PER_PAGE = 3;
	private static final String COMMAND = "command";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AnimalService animalService = serviceFactory.getAnimalService();

		List<Animal> animals = animalService.findAll();

		int offset = getOffset(request, LIMIT_ARTICLES_PER_PAGE);
		String requestUrl = request.getRequestURI();

		int limitOfList = LIMIT_ARTICLES_PER_PAGE + offset;
		if (limitOfList >= animals.size()) {
			limitOfList = animals.size();
		}
		List<Animal> currentAnimals = animals.subList(offset, limitOfList);

		Pagination pagination = new Pagination.Builder(requestUrl + "?", offset, animals.size() - 1,
				request.getParameter(COMMAND)).withLimit(LIMIT_ARTICLES_PER_PAGE).build();

		request.setAttribute(PAGINATION, pagination);
		request.setAttribute(ANIMALS, currentAnimals);

		LOGGER.info("User was viewing all animals.");
		return JspPath.VIEW_ANIMALS.getUrl();
	}

	public final int getOffset(HttpServletRequest req, int limit) {
		String val = req.getParameter(PAGE);
		if (val != null) {
			int page = Integer.parseInt(val);
			return (page - 1) * limit;
		} else {
			return 0;
		}
	}
}
