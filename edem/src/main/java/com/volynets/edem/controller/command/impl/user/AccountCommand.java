package com.volynets.edem.controller.command.impl.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.entity.Account;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AccountService;
import com.volynets.edem.service.factory.ServiceFactory;

/**
 * Defines command for view personal information about user.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class AccountCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(TakeActionCommand.class);

	private static final String USER = "user";
	private static final String NAME = "account_name";
	private static final String SURNAME = "account_surname";
	private static final String EMAIL = "account_email";
	private static final String AVATAR = "account_avatar";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AccountService accountService = serviceFactory.getAccountService();
		
		User user = (User) request.getSession().getAttribute(USER);
		int idUser = user.getId();
				
		Account account = accountService.findById(idUser);
		String name = account.getName();
		String surname = account.getSurname();
		
		request.setAttribute(NAME, name);
		request.setAttribute(SURNAME, surname);
		request.setAttribute(EMAIL, user.getEmail());
		request.setAttribute(AVATAR, account.getAvatar());
		
		LOGGER.info("User " + surname + " " + name + " watched personal infprmation.");
		return JspPath.ACCOUNT.getUrl();
	}

}
