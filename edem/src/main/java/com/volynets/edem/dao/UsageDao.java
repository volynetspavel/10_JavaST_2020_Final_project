package com.volynets.edem.dao;

import com.volynets.edem.entity.Usage;
import com.volynets.edem.exception.DaoException;

/**
 * This class is a layer for interacting with usage database.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public abstract class UsageDao extends AbstractDao<Usage> {
	public abstract int sumCo2ForUser(int idUser, int idAnimal) throws DaoException;

	public abstract void deleteByIdAccount(int idAccount) throws DaoException;
	
	public abstract void deleteByIdAction(int idAction) throws DaoException;
	
	public abstract void deleteByIdAnimal(int idAnimal) throws DaoException;
}