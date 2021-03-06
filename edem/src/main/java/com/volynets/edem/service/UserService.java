package com.volynets.edem.service;

import java.util.List;

import com.volynets.edem.entity.Account;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.ServiceException;

/**
 * This class is a layer for interacting with UserDao.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public interface UserService {
	void registration(String surname, String name, String avatar, String email, String password)
			throws ServiceException;

	List<User> findByEmailAndPass(String email, String password) throws ServiceException;

	void insertUser(User user) throws ServiceException;

	boolean containsEmail(String email) throws ServiceException;

	User findByEmail(String email) throws ServiceException;
	
	List<User> findAll() throws ServiceException;
}
