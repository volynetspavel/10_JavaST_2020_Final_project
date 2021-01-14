package com.volynets.edem.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.connection.ConnectionPool;
import com.volynets.edem.dao.AnimalDao;
import com.volynets.edem.dao.AnimalDao;
import com.volynets.edem.dao.CommentDao;
import com.volynets.edem.dao.UsageDao;
import com.volynets.edem.dao.UserDao;
import com.volynets.edem.dao.transaction.EntityTransaction;
import com.volynets.edem.entity.Animal;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.DaoException;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AbstractService;
import com.volynets.edem.service.AnimalService;

/**
 * This class is an implementation of AnimalService.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class AnimalServiceImpl extends AbstractService implements AnimalService {
	private static final Logger LOGGER = LogManager.getLogger(AnimalServiceImpl.class);

	private AnimalDao animalDao;

	public AnimalServiceImpl() {
		this.animalDao = daoFactory.getAnimalDao();
	}

	@Override
	public Animal findByName(String name) throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		animalDao.setConnection(connection);
		Animal animal = null;

		try {
			animal = animalDao.findByName(name);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return animal;
	}

	@Override
	public List<Animal> findAll() throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		animalDao.setConnection(connection);
		List<Animal> animals = null;
		
		try {
			animals = animalDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return animals;
	}

	@Override
	public void delete(int idAnimal) throws ServiceException {
		EntityTransaction transaction = new EntityTransaction();
		
		UsageDao usageDao = daoFactory.getUsageDao();
		
		try {
			transaction.initTransaction(usageDao, animalDao);
			usageDao.deleteByIdAnimal(idAnimal);
			animalDao.delete(idAnimal);

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
	public Animal findById(int id) throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		animalDao.setConnection(connection);
		try {
			return animalDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}

	@Override
	public void update(Animal animal) throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		animalDao.setConnection(connection);
		try {
			animalDao.update(animal);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}

}
