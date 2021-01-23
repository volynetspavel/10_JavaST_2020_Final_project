package com.volynets.edem.controller.command.impl.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.controller.command.impl.SignOutCommand;
import com.volynets.edem.dao.UsageDao;
import com.volynets.edem.entity.Action;
import com.volynets.edem.entity.Animal;
import com.volynets.edem.entity.Comment;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.ActionService;
import com.volynets.edem.service.AnimalService;
import com.volynets.edem.service.CommentService;
import com.volynets.edem.service.UsageService;
import com.volynets.edem.service.factory.ServiceFactory;

/**
 * This class is used to add action and animal into user account.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class TakeActionCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(TakeActionCommand.class);

	private static final String USER = "user";
	private static final String ACTION_COMPLETE = "actionComplete";
	private static final String TITLE_ACTION = "title_action";
	
	private static final String LOGO = "logo";
	private static final String TITLE = "title";
	private static final String CO2 = "co2";
	private static final String COMMENTS = "comments";
	private static final String CONTENT = "content";
	private static final String LIST_COMMENTS = "list_comments";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UsageService usageService = serviceFactory.getUsageService();
		AnimalService animalService = serviceFactory.getAnimalService();
		
		int idUser = ((User) request.getSession().getAttribute(USER)).getId();
		
		int idAnimal = usageService.findIdAnimalByIdUser(idUser);		
		String actionTitle = request.getParameter(TITLE_ACTION);
		String email = ((User) request.getSession().getAttribute(USER)).getEmail();
		Animal animal = animalService.findById(idAnimal);
		
		usageService.takeAction(idAnimal, actionTitle, email);
		
		request.setAttribute(ACTION_COMPLETE, "Action completed successfully");
		
		ActionService actionService = serviceFactory.getActionService();
		CommentService commentService = serviceFactory.getCommentService();

		Action action = actionService.findByTitle(actionTitle);
		request.setAttribute(LOGO, action.getLogo());
		request.setAttribute(TITLE, action.getTitle());
		request.setAttribute(CO2, action.getCo2());
		
		request.setAttribute(COMMENTS, action.getComments());
		request.setAttribute(CONTENT, action.getContent());
		
		List<Comment> listComments = commentService.findByActionId(action.getId());
		request.setAttribute(LIST_COMMENTS, listComments);
		
		LOGGER.debug("Action -" + actionTitle + " for animal -  " + animal.getName() + "successfully added.");
		return JspPath.ACTION.getUrl();
	}
}
