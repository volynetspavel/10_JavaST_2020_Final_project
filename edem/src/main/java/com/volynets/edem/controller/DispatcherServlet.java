package com.volynets.edem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.connection.ConnectionPool;
import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.CommandFactory;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.exception.ConnectionPoolException;
import com.volynets.edem.exception.ServiceException;

/**
 * DispatcherServlet is used to retrieve command from request, correlate the
 * command with the corresponding class and execute this command.
 *
 * @author Pavel Volynets
 * @version 1.0
 */
@WebServlet(urlPatterns = { "/sign_in", "/sign_out", "/view_actions", "/view_accounts", "/watch_action",
		"/view_animals", "/take_action", "/visit_registration", "/registration", "/language",
		"/delete_account", "/delete_action", "/delete_animal", "/take_animal", "/visit_add_action",
		"/add_action", "/add_comment"})
@MultipartConfig
public class DispatcherServlet extends HttpServlet {
	private static final Logger LOGGER = LogManager.getLogger(DispatcherServlet.class);

	private static final String COMMAND = "command";
	private static final String ERROR = "error";
	private static final String STATUS_CODE = "statusCode";

	@Override
	public void init() throws ServletException {
		ConnectionPool.getInstance();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page;

		CommandFactory commandFactory = CommandFactory.getInstance();
		try {
			if (request.getParameter(COMMAND) != null) {
				LOGGER.info("Request. Parameter command = " + request.getParameter(COMMAND));
				Command command = commandFactory.receiveCommand(request.getParameter(COMMAND));
				page = command.execute(request, response);
			} else {
				LOGGER.error("Command not received");
				request.setAttribute(ERROR, "Command not received");
				request.setAttribute(STATUS_CODE, 404);
				page = JspPath.ERROR.getUrl();
			}
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage(), e);
			request.setAttribute(ERROR, e.getMessage());
			request.setAttribute(STATUS_CODE, 500);
			page = JspPath.ERROR.getUrl();
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	@Override
	public void destroy() {
		try {
			ConnectionPool.getInstance().closePool();
		} catch (ConnectionPoolException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	public String getCurrentJspPath() {
		return getServletContext().getRealPath("");
	}
}
