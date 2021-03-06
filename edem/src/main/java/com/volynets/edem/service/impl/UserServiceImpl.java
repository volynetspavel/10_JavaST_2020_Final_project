package com.volynets.edem.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Formatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.connection.ConnectionPool;
import com.volynets.edem.dao.AccountDao;
import com.volynets.edem.dao.UserDao;
import com.volynets.edem.dao.transaction.EntityTransaction;
import com.volynets.edem.entity.Account;
import com.volynets.edem.entity.Role;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.DaoException;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AbstractService;
import com.volynets.edem.service.UserService;

/**
 * This class is an implementation of UserService.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class UserServiceImpl extends AbstractService implements UserService {
	private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

	private UserDao userDao;

	public UserServiceImpl() {
		this.userDao = daoFactory.getUserDao();
	}

	@Override
	public void registration(String surname, String name, String avatar, String email, String password)
			throws ServiceException {
		EntityTransaction transaction = new EntityTransaction();
		AccountDao accountDao = daoFactory.getAccountDao();

		User newUser = new User();
		Account newAccount = new Account();
		String hashPassword = md5(password);
		
		try {
			transaction.initTransaction(userDao, accountDao);

			newUser.setEmail(email);
			newUser.setRole(Role.USER);
			newUser.setPassword(hashPassword);

			userDao.insert(newUser);
			User user = userDao.findByEmailAndPass(email, hashPassword).get(0);

			newAccount.setSurname(surname);
			newAccount.setName(name);
			newAccount.setAvatar(avatar);
			newAccount.setUser(user);

			accountDao.insert(newAccount);

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
	public List<User> findByEmailAndPass(String email, String password) throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		userDao.setConnection(connection);
		List<User> users = null;
		
		try {
			users = userDao.findByEmailAndPass(email, md5(password));
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return users;
	}

	@Override
	public void insertUser(User user) throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		userDao.setConnection(connection);
		try {
			String hashPassword = md5(user.getPassword());
			user.setPassword(hashPassword);
			userDao.insert(user);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}

	@Override
	public boolean containsEmail(String email) throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		userDao.setConnection(connection);
		try {
			return userDao.containsEmail(email);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}

	private String md5(String string) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("md5");
			digest.reset();
			digest.update(string.getBytes());
			byte hash[] = digest.digest();
			Formatter formatter = new Formatter();
			for (int i = 0; i < hash.length; i++) {
				formatter.format("%02X", hash[i]);
			}
			String md5summ = formatter.toString();
			formatter.close();
			return md5summ.toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	@Override
	public User findByEmail(String email) throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		userDao.setConnection(connection);
		User user = null;
		
		try {
			user = userDao.findByEmail(email);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
		return user;
	}

	@Override
	public List<User> findAll() throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		userDao.setConnection(connection);
		List<User> users = null;
		
		try {
			users = userDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return users;
	}
}
