package com.volynets.edem.service.factory;

import com.volynets.edem.service.*;
import com.volynets.edem.service.impl.*;
import com.volynets.edem.service.UserService;
import com.volynets.edem.service.impl.UserServiceImpl;

/**
 * Factory for creating service-objects.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public final class ServiceFactory {
	private static final ServiceFactory INSTANCE = new ServiceFactory();

	private final AccountService accountService = new AccountServiceImpl();
	private final ActionService actionService = new ActionServiceImpl();
	private final AnimalService animalService = new AnimalServiceImpl();
	private final CommentService commentService = new CommentServiceImpl();
	private final UsageService usageService = new UsageServiceImpl();
	private final UserService userService = new UserServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return INSTANCE;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public ActionService getActionService() {
		return actionService;
	}

	public AnimalService getAnimalService() {
		return animalService;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public UsageService getUsageService() {
		return usageService;
	}

	public UserService getUserService() {
		return userService;
	}
}
