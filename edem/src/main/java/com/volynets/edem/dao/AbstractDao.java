package com.volynets.edem.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import com.volynets.edem.entity.AbstractEntity;
import com.volynets.edem.exception.DaoException;

/**
 * This class is a basic class of dao-layer for interacting with database.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public abstract class AbstractDao<T extends AbstractEntity> {
	protected Connection connection;

	public abstract void insert(T entity) throws DaoException;

	public abstract void delete(int id) throws DaoException;

	public abstract void update(T entity) throws DaoException;

	public abstract T findById(int id) throws DaoException;

	public abstract List<T> findAll() throws DaoException;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LogManager.getLogger(AbstractDao.class).error(e.getMessage(), e);
			}
		}
	}

	public void closePreparedStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LogManager.getLogger(AbstractDao.class).error(e.getMessage(), e);
			}
		}
	}
}
