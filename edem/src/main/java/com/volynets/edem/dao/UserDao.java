package com.volynets.edem.dao;

import java.util.List;

import com.volynets.edem.entity.User;
import com.volynets.edem.exception.DaoException;

/**
 * This class is a layer for interacting with user database.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public abstract class UserDao extends AbstractDao<User> {

    public abstract List<User> findByEmailAndPass(String email, String password) throws DaoException;
    
    public abstract boolean containsEmail(String email) throws DaoException;
}
