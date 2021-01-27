package com.volynets.edem.service;

import org.testng.annotations.Test;

import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.factory.ServiceFactory;
import com.volynets.edem.service.impl.UsageServiceImpl;

public class TestUsageService {
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UsageService usageService = serviceFactory.getUsageService();

	private int idAnimal = 4;
	private String actionName = "Reusable Bags";
	private String email = "user1@gmail.com";

	@Test
	public void testTakeAction() throws ServiceException {
		usageService.takeAction(idAnimal, actionName, email);
	}

}
