package com.volynets.edem.controller.command.impl.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.CommandFactory;
import com.volynets.edem.controller.command.CommandType;
import com.volynets.edem.entity.Action;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.ActionService;
import com.volynets.edem.service.factory.ServiceFactory;

/**
 * This class is used to delete action.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class DeleteActionCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(DeleteActionCommand.class);

	private static final String ID_ACTION = "id_action";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ActionService actionService = serviceFactory.getActionService();
		
		int idAction = Integer.parseInt(request.getParameter(ID_ACTION));
		Action action = actionService.findById(idAction);
		
		actionService.delete(idAction);
		
		LOGGER.info("Action with id = " + idAction + ", title: " 
					+ action.getTitle() + " deleted");
		
		CommandFactory commandFactory = CommandFactory.getInstance();
		Command command = commandFactory.receiveCommand(CommandType.VIEW_ACTIONS);
		return command.execute(request, response);
	}
}
