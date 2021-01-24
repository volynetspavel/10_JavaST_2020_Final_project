package com.volynets.edem.controller.command.impl.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.entity.Action;
import com.volynets.edem.entity.Comment;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.ActionService;
import com.volynets.edem.service.CommentService;
import com.volynets.edem.service.factory.ServiceFactory;

/**
 * This class is used to watch action by user.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class WatchActionCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(WatchActionCommand.class);

	private static final String ID_ACTION = "id_action";
	private static final String USER = "user";
	private static final String LOGO = "logo";
	private static final String TITLE = "title";
	private static final String CO2 = "co2";
	private static final String COMMENTS = "comments";
	private static final String CONTENT = "content";
	private static final String ID = "id";
	private static final String LIST_COMMENTS = "list_comments";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ActionService actionService = serviceFactory.getActionService();
		CommentService commentService = serviceFactory.getCommentService();

		int idAction = Integer.parseInt(request.getParameter(ID_ACTION));
		Action action = actionService.findById(idAction);
		request.setAttribute(LOGO, action.getLogo());
		request.setAttribute(TITLE, action.getTitle());
		request.setAttribute(CO2, action.getCo2());
		
		request.setAttribute(COMMENTS, action.getComments());
		request.setAttribute(CONTENT, action.getContent());
		request.setAttribute(ID, idAction);
		
		List<Comment> listComments = commentService.findByActionId(idAction);
		request.setAttribute(LIST_COMMENTS, listComments);
		
		User user = ((User) request.getSession().getAttribute(USER));
		LOGGER.info("User " + user.getEmail() + " was watching action " + action.getTitle());
		return JspPath.ACTION.getUrl();
	}

}
