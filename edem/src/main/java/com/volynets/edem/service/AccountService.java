package com.volynets.edem.service;

import java.util.List;

import com.volynets.edem.entity.Account;
import com.volynets.edem.exception.ServiceException;

/**
 * This class is a layer for interacting with AccountDao.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public interface AccountService {
	Account findById(int id) throws ServiceException;

	void insertAccount(Account account) throws ServiceException;

	List<Account> findAll() throws ServiceException;

	void delete(int id) throws ServiceException;
}
