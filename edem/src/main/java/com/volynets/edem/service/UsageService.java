package com.volynets.edem.service;

import java.util.List;

import com.volynets.edem.entity.Action;
import com.volynets.edem.entity.Animal;
import com.volynets.edem.exception.ServiceException;

/**
 * This class is a layer for interacting with UsageDao.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public interface UsageService {
	void takeAction(int idAnimal, String actionName, String email) throws ServiceException;

	int sumCo2ForUser(int idUser, int idAnimal) throws ServiceException;
	
	int findIdAnimalByIdUser(int idUser) throws ServiceException;

	Animal findAnimalByIdUser(int idUser) throws ServiceException;
	
	void takeAnimal(int idAnimal, int idUser) throws ServiceException;

	List<Action> findActionsByUserId(int idUser) throws ServiceException;
}
