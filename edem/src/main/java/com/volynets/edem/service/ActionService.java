package com.volynets.edem.service;

import java.util.List;

import com.volynets.edem.entity.Action;
import com.volynets.edem.exception.ServiceException;

/**
 * This class is a layer for interacting with ActionDao.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public interface ActionService {
	Action findById(int id) throws ServiceException;

	List<Action> findAll() throws ServiceException;

	void delete(int id) throws ServiceException;

	void update(Action action) throws ServiceException;

	Action findByTitle(String title) throws ServiceException;
}
