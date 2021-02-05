package com.volynets.edem.dao;

import java.util.List;

import com.volynets.edem.entity.Action;
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
	
	public abstract int findIdAnimalByIdUser(int idUser) throws DaoException;
	
	public abstract void takeAnimal(int idAnimal, int idUser) throws DaoException;
	
	public abstract List<Action> findActionsByUserId(int idUser) throws DaoException;
}
