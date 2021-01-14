package com.volynets.edem.service;

import com.volynets.edem.exception.ServiceException;

/**
 * This class is a layer for interacting with UsageDao.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public interface UsageService {
	void takeAction(String animalName, String actionName, String email) throws ServiceException;

	int sumCo2ForUser(int idUser, int idAnimal) throws ServiceException;
	
}
