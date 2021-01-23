package com.volynets.edem.controller.command.impl.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.controller.command.impl.ViewAllActionsCommand;
import com.volynets.edem.entity.Account;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AccountService;
import com.volynets.edem.service.factory.ServiceFactory;

/**
 * This class is used for view all accounts.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class ViewAllAccountsCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(ViewAllActionsCommand.class);

	private static final String ACCOUNTS = "accounts";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AccountService accountService = serviceFactory.getAccountService();

		List<Account> accounts = accountService.findAll();
		request.setAttribute(ACCOUNTS, accounts);

		return JspPath.VIEW_ACCOUNTS.getUrl();
	}
}
