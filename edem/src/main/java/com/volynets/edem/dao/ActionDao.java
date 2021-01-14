package com.volynets.edem.dao;

import com.volynets.edem.entity.Action;
import com.volynets.edem.exception.DaoException;

/**
 * This class is a layer for interacting with action database.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public abstract class ActionDao extends AbstractDao<Action> {
	public abstract Action findByTitle(String name) throws DaoException;
}
