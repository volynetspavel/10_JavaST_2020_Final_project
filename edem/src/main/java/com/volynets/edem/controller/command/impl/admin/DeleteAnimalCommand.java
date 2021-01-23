package com.volynets.edem.controller.command.impl.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.CommandFactory;
import com.volynets.edem.controller.command.CommandType;
import com.volynets.edem.entity.Animal;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AnimalService;
import com.volynets.edem.service.factory.ServiceFactory;

/**
 * This class is used to delete animal.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class DeleteAnimalCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(DeleteAnimalCommand.class);

	private static final String ID_ANIMAL = "id_animal";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AnimalService animalService = serviceFactory.getAnimalService();
		
		int idAnimal = Integer.parseInt(request.getParameter(ID_ANIMAL));
		Animal animal = animalService.findById(idAnimal);
		
		animalService.delete(idAnimal);
		
		LOGGER.info("Animal with id = " + idAnimal + ", title: " 
					+ animal.getName() + " deleted");
		
		CommandFactory commandFactory = CommandFactory.getInstance();
		Command command = commandFactory.receiveCommand(CommandType.VIEW_ANIMALS);
		return command.execute(request, response);
	}
}
