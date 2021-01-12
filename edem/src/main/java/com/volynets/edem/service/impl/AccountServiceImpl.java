package com.volynets.edem.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.connection.ConnectionPool;
import com.volynets.edem.dao.AccountDao;
import com.volynets.edem.entity.Account;
import com.volynets.edem.exception.DaoException;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AbstractService;
import com.volynets.edem.service.AccountService;

/**
 * This class is an implementation of AccountService.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class AccountServiceImpl extends AbstractService implements AccountService {
	private static final Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class);

	private AccountDao accountDao;

	public AccountServiceImpl() {
		this.accountDao = daoFactory.getAccountDao();
	}

	@Override
	public Account findById(int id) throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		accountDao.setConnection(connection);
		try {
			return accountDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}

	@Override
	public void insertAccount(Account account) throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		accountDao.setConnection(connection);
		try {
			accountDao.insert(account);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}

}
