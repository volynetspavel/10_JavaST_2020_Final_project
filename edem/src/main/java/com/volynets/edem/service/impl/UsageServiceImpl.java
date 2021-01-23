package com.volynets.edem.service.impl;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.connection.ConnectionPool;
import com.volynets.edem.dao.AccountDao;
import com.volynets.edem.dao.ActionDao;
import com.volynets.edem.dao.AnimalDao;
import com.volynets.edem.dao.UsageDao;
import com.volynets.edem.dao.UserDao;
import com.volynets.edem.dao.transaction.EntityTransaction;
import com.volynets.edem.entity.Account;
import com.volynets.edem.entity.Action;
import com.volynets.edem.entity.Animal;
import com.volynets.edem.entity.Usage;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.DaoException;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AbstractService;
import com.volynets.edem.service.UsageService;

/**
 * This class is an implementation of UsageService.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class UsageServiceImpl extends AbstractService implements UsageService {
	private static final Logger LOGGER = LogManager.getLogger(UsageServiceImpl.class);

	private UsageDao usageDao;

	public UsageServiceImpl() {
		this.usageDao = daoFactory.getUsageDao();
	}

	@Override
	public void takeAction(int idAnimal, String actionTitle, String email) throws ServiceException {
		EntityTransaction transaction = new EntityTransaction();

		AnimalDao animalDao = daoFactory.getAnimalDao();
		ActionDao actionDao = daoFactory.getActionDao();
		UserDao userDao = daoFactory.getUserDao();
		AccountDao accountDao = daoFactory.getAccountDao();

		Animal animal = null;
		Action action = null;
		User user = null;
		Account account = null;
		Usage usage = new Usage();

		try {
			transaction.initTransaction(usageDao, animalDao, actionDao, userDao, accountDao);
			animal = animalDao.findById(idAnimal);
			action = actionDao.findByTitle(actionTitle);
			user = userDao.findByEmail(email);
			account = accountDao.findById(user.getId());

			usage.setAnimal(animal);
			usage.setAction(action);
			usage.setAccount(account);
			usage.setReducedCO2(action.getCo2());

			usageDao.insert(usage);

			transaction.commit();
		} catch (DaoException e) {
			try {
				transaction.rollback();
			} catch (DaoException e1) {
				throw new ServiceException(e);
			}
			throw new ServiceException(e);
		} finally {
			try {
				transaction.endTransaction();
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
	}

	@Override
	public int sumCo2ForUser(int idUser, int idAnimal) throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		usageDao.setConnection(connection);
		int sumCo2;

		try {
			sumCo2 = usageDao.sumCo2ForUser(idUser, idAnimal);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
		return sumCo2;
	}

	@Override
	public int findIdAnimalByIdUser(int idUser) throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		usageDao.setConnection(connection);
		int idAnimal;

		try {
			idAnimal = usageDao.findIdAnimalByIdUser(idUser);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
		return idAnimal;
	}
}
