package com.volynets.edem.service;

import java.util.List;

import com.volynets.edem.entity.Animal;
import com.volynets.edem.exception.ServiceException;

/**
 * This class is a layer for interacting with AnimalDao.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public interface AnimalService {
	Animal findByName(String name) throws ServiceException;

	List<Animal> findAll() throws ServiceException;
	
	void delete(int id) throws ServiceException;
	
	Animal findById(int id) throws ServiceException;
	
	void update(Animal animal) throws ServiceException;
	
	void addAnimal(String name, String desc, String content, String logo, int countCO2) throws ServiceException;
}
