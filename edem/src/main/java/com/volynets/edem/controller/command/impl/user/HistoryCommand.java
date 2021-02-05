package com.volynets.edem.controller.command.impl.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.entity.Action;
import com.volynets.edem.entity.Animal;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AnimalService;
import com.volynets.edem.service.UsageService;
import com.volynets.edem.service.factory.ServiceFactory;

/**
 * Defines command for view history of actions.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class HistoryCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(TakeActionCommand.class);

	private static final String USER = "user";
	private static final String LOGO = "animal_logo";
	private static final String NAME = "animal_name";
	private static final String DESCRIPTION = "description";
	private static final String CONTENT = "content";
	private static final String ANIMAL_CO2 = "animal_co2";
	private static final String TOTAL_CO2 = "total_co2";
	private static final String LIST_ACTIONS = "list_actions";

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UsageService usageService = serviceFactory.getUsageService();
		AnimalService animalService = serviceFactory.getAnimalService();
		
		User user = (User) request.getSession().getAttribute(USER);
		int idUser = user.getId();
		String emailString = user.getEmail();
		int idAnimalInTableByUserId = usageService.findIdAnimalByIdUser(idUser);
		int totalCO2 = usageService.sumCo2ForUser(idUser, idAnimalInTableByUserId);
		
		Animal animal = animalService.findById(idAnimalInTableByUserId);
		
		request.setAttribute(LOGO, animal.getLogo());
		request.setAttribute(NAME, animal.getName());
		request.setAttribute(DESCRIPTION, animal.getDesc());
		request.setAttribute(CONTENT, animal.getContent());
		request.setAttribute(ANIMAL_CO2, animal.getCo2());
		request.setAttribute(TOTAL_CO2, serviceFactory);
		request.setAttribute(TOTAL_CO2, totalCO2);
		
		List<Action> actions = usageService.findActionsByUserId(idUser);
		request.setAttribute(LIST_ACTIONS, actions);
		
		LOGGER.info("User " + emailString + " watched history of account.");
		return JspPath.HISTORY.getUrl();
	}
}
