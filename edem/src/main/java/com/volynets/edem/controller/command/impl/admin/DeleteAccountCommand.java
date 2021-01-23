package com.volynets.edem.controller.command.impl.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.CommandFactory;
import com.volynets.edem.controller.command.CommandType;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.controller.command.impl.ViewAllActionsCommand;
import com.volynets.edem.entity.Account;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AccountService;
import com.volynets.edem.service.factory.ServiceFactory;

/**
 * This class is used to delete account.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class DeleteAccountCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(DeleteAccountCommand.class);

	private static final String ID_ACCOUNT = "id_account";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {	
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AccountService accountService = serviceFactory.getAccountService();
		
		int idAccount = Integer.parseInt(request.getParameter(ID_ACCOUNT));
		Account account = accountService.findById(idAccount);
		
		accountService.delete(idAccount);
		
		LOGGER.info("Account with id = " + idAccount + ", surname: " 
					+ account.getSurname() + ", name: " + account.getName() + " deleted");
		
		CommandFactory commandFactory = CommandFactory.getInstance();
		Command command = commandFactory.receiveCommand(CommandType.VIEW_ACCOUNTS);
		return command.execute(request, response);
	}
}

