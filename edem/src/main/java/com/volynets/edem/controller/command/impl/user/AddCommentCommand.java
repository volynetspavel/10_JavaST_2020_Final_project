package com.volynets.edem.controller.command.impl.user;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
 * This class is used for adding new comment.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class AddCommentCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(AddCommentCommand.class);
	
	private static final String USER = "user";
    private static final String COMMENT_CONTENT = "comment_content";
	private static final String ID_ACTION = "id_action";
	
	private static final String LOGO = "logo";
	private static final String TITLE = "title";
	private static final String CO2 = "co2";
	private static final String COMMENTS = "comments";
	private static final String CONTENT = "content";
	private static final String ID = "id";
	private static final String LIST_COMMENTS = "list_comments";

    
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		String page;
		
		User user = (User) request.getSession().getAttribute(USER);
		
		String content = request.getParameter(COMMENT_CONTENT);
		int idAccount = user.getId();
		int idAction = Integer.parseInt(request.getParameter(ID_ACTION)); 
		
		LocalDateTime time = LocalDateTime.now();
		Timestamp createdTime = Timestamp.valueOf(time);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		CommentService commentService = serviceFactory.getCommentService();
		commentService.addNewComment(content, createdTime, idAccount, idAction);
		LOGGER.info("User " + user.getEmail() + " added comment for action " + idAction);
		
		
		ActionService actionService = serviceFactory.getActionService();

		Action action = actionService.findById(idAction);
		request.setAttribute(LOGO, action.getLogo());
		request.setAttribute(TITLE, action.getTitle());
		request.setAttribute(CO2, action.getCo2());
		
		request.setAttribute(COMMENTS, action.getComments());
		request.setAttribute(CONTENT, action.getContent());
		request.setAttribute(ID, idAction);
		
		List<Comment> listComments = commentService.findByActionId(idAction);
		request.setAttribute(LIST_COMMENTS, listComments);
		
		return JspPath.ACTION.getUrl();
	}

}
