package com.volynets.edem.service;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.volynets.edem.entity.Action;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.factory.ServiceFactory;

public class TestActionService {
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private ActionService actionService = serviceFactory.getActionService();
	
	private int id = 5;
	private Action actionForUpdate = new Action();
	
	@BeforeClass
	public void setUpActionForUpdate() {

		String title = "Public Transit";
		String desc = "Public transportation helps reduce gridlock and carbon emissions.";
		String content = "Riding the rails and taking the bus can add productive tranquility"
				+ " to your commute hours, as well as save you substantial carbon and money"
				+ " over time, particularly if you live in an urban area where you can do "
				+ "away with your car entirely.";
		String logo = "/img/action/Public-Transit.jpg";
		int comment = 2;
		int co2 = 20;

		actionForUpdate.setId(id);
		actionForUpdate.setTitle(title);
		actionForUpdate.setDesc(desc);
		actionForUpdate.setContent(content);
		actionForUpdate.setLogo(logo);
		actionForUpdate.setComments(comment);
		actionForUpdate.setCo2(co2);
	}
	
	@Test(description = "Check delete of object from database.")
	public void testDeleteAction() throws ServiceException {
		int idAction = 4;
		actionService.delete(idAction);
		
		Action action = null;
		action = actionService.findById(idAction);
		
		Assert.assertNull(action);
	}
	
	@Test(description = "Check update of object from database.")
	public void testUpdate() throws ServiceException {
		actionService.update(actionForUpdate);
	}
	
	public void testFindById() throws ServiceException {
		actionService.findById(id);
	}
}
