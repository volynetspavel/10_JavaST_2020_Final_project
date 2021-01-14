package com.volynets.edem.dao.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.connection.ConnectionPool;
import com.volynets.edem.dao.AbstractDao;
import com.volynets.edem.exception.DaoException;

/**
 * The transaction that helps to handle different transactions with multiple
 * dao.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class EntityTransaction {
	private static final Logger LOGGER = LogManager.getLogger(EntityTransaction.class);
	private Connection connection;

	public void initTransaction(AbstractDao dao, AbstractDao... daos) throws DaoException {
		if (connection == null) {
			connection = ConnectionPool.getInstance().takeConnection();
		}
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		dao.setConnection(connection);
		for (AbstractDao daoElement : daos) {
			daoElement.setConnection(connection);
		}
	}

	public void endTransaction() throws DaoException {
		if (connection != null) {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				LOGGER.error("It is imposible to set auto-commit on true.", e);
				throw new DaoException(e);
			}
		}
		ConnectionPool.getInstance().returnConnection(connection);
		connection = null;
	}

	public void commit() throws DaoException {
		try {
			connection.commit();
		} catch (SQLException e) {
			LOGGER.error("It is impossible to commit transaction", e);
			throw new DaoException(e);
		}
	}

	public void rollback() throws DaoException {
		try {
			connection.rollback();
		} catch (SQLException e) {
			LOGGER.error("It is impossible to rollback transaction", e);
			throw new DaoException(e);
		}
	}
}