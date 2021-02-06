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
import com.volynets.edem.tag.Pagination;

/**
 * This class is used for view all accounts.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class ViewAllAccountsCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(ViewAllActionsCommand.class);

	private static final String ACCOUNTS = "accounts";
	private static final String PAGINATION = "pagination";
	private static final String PAGE = "page";
	private static final int LIMIT_ARTICLES_PER_PAGE = 3;
	private static final String COMMAND = "command";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AccountService accountService = serviceFactory.getAccountService();

		List<Account> accounts = accountService.findAll();

		int offset = getOffset(request, LIMIT_ARTICLES_PER_PAGE);
		String requestUrl = request.getRequestURI();

		int limitOfList = (LIMIT_ARTICLES_PER_PAGE + offset >= accounts.size()) ? accounts.size()
				: LIMIT_ARTICLES_PER_PAGE + offset;

		List<Account> currentAccounts = accounts.subList(offset, limitOfList);

		Pagination pagination = new Pagination.Builder(requestUrl + "?", offset, accounts.size() - 1,
				request.getParameter(COMMAND)).withLimit(LIMIT_ARTICLES_PER_PAGE).build();

		request.setAttribute(PAGINATION, pagination);
		request.setAttribute(ACCOUNTS, currentAccounts);

		LOGGER.info("Admin was viewing all accounts.");
		return JspPath.VIEW_ACCOUNTS.getUrl();
	}

	public final int getOffset(HttpServletRequest req, int limit) {
		String val = req.getParameter(PAGE);
		if (val != null) {
			int page = Integer.parseInt(val);
			return (page - 1) * limit;
		} else {
			return 0;
		}
	}
}
