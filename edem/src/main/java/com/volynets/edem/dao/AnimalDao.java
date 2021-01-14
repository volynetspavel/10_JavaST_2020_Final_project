package com.volynets.edem.dao;

import com.volynets.edem.entity.Animal;
import com.volynets.edem.exception.DaoException;

/**
 * This class is a layer for interacting with animal database.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public abstract class AnimalDao extends AbstractDao<Animal> {
    public abstract Animal findByName(String name) throws DaoException;
    	 
}
