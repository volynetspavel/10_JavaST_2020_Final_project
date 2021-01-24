package com.volynets.edem.controller.command.impl.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.entity.Animal;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AnimalService;
import com.volynets.edem.service.UsageService;
import com.volynets.edem.service.factory.ServiceFactory;

/**
 * This class is used for taking animal into user account by user choice.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class TakeAnimalCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(TakeAnimalCommand.class);

	private static final String USER = "user";
	private static final String ID_ANIMAL = "id_animal";
	private static final String ANIMAL_CHOSE = "animal_chose";
	private static final String ANIMAL_CHOSE_ERROR = "animal_chose_error";
	private static final String ANIMALS = "animals";
	private static final String CURRENT_ID = "current_id";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UsageService usageService = serviceFactory.getUsageService();
		
		int idUser = ((User) request.getSession().getAttribute(USER)).getId();
		int idAnimal = Integer.parseInt(request.getParameter(ID_ANIMAL));
		
		int idAnimalInTableByUserId = usageService.findIdAnimalByIdUser(idUser);
		if (idAnimalInTableByUserId == 0) {
			usageService.takeAnimal(idAnimal, idUser);
			
			request.setAttribute(ANIMAL_CHOSE, "Animal have choosen successfully");
			LOGGER.debug("Animal №" + idAnimal + " for user " + idUser + "successfully added.");
		} else {
			request.setAttribute(CURRENT_ID, idAnimal);
			request.setAttribute(ANIMAL_CHOSE_ERROR, "You have already chosen animal.");
			LOGGER.debug("User " + idUser + " tried to add second animal.");
		}

		AnimalService animalService = serviceFactory.getAnimalService();
		List<Animal> animals = animalService.findAll();
		request.setAttribute(ANIMALS, animals);

		return JspPath.VIEW_ANIMALS.getUrl();
	}

}
