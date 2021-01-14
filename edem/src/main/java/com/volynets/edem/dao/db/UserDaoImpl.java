package com.volynets.edem.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.dao.UserDao;
import com.volynets.edem.entity.Role;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.DaoException;

/**
 * This class is an implementation of UserDao.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class UserDaoImpl extends UserDao {
	private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

	private static final String SELECT_ALL_USERS = "SELECT * FROM edem_db.user";
	private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM edem_db.user WHERE id=?";
	private static final String SQL_DELETE_USER = "DELETE FROM edem_db.user WHERE id=?";
	private static final String SQL_UPDATE_USER = "UPDATE edem_db.user "
			+ "SET `email`=?, `password`=?, `role`=? WHERE id=?";
	private static final String SQL_INSERT_USER = "INSERT into edem_db.user "
			+ "(`email`,`password`,`role`) VALUES (?, ?, ?)";
	private static final String SQL_FIND_USER_BY_EMAIL_AND_PASS = "SELECT * FROM edem_db.user "
			+ "WHERE email=? AND password=? ";
	private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM edem_db.user WHERE email=?";

	@Override
	public void insert(User entity) throws DaoException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
			preparedStatement.setString(1, entity.getEmail());
			preparedStatement.setString(2, entity.getPassword());
			preparedStatement.setString(3, entity.getRole().getRole());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public void update(User entity) throws DaoException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
			preparedStatement.setString(1, entity.getEmail());
			preparedStatement.setString(2, entity.getPassword());
			preparedStatement.setString(3, entity.getRole().getRole());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public void delete(int id) throws DaoException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public User findById(int id) throws DaoException {
		User user = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setEmail(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setRole(Role.valueOf(resultSet.getString(4)));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		return user;
	}

	@Override
	public List<User> findAll() throws DaoException {
		List<User> users = new ArrayList<User>();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				User user = new User();

				user.setId(resultSet.getInt(1));
				user.setEmail(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setRole(Role.valueOf(resultSet.getString(4)));

				users.add(user);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		return users;
	}

	@Override
	public List<User> findByEmailAndPass(String email, String password) throws DaoException {
		List<User> users = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL_AND_PASS);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				User user = new User();

				user.setId(resultSet.getInt(1));
				user.setEmail(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setRole(Role.valueOf(resultSet.getString(4)));

				users.add(user);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);

		return users;
	}

	@Override
	public boolean containsEmail(String email) throws DaoException {
		return dbContains(email, SQL_FIND_USER_BY_EMAIL);
	}

	private boolean dbContains(String value, String sqlQuery) throws DaoException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, value);

			resultSet = preparedStatement.executeQuery();

			return resultSet.next();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public User findByEmail(String email) throws DaoException {
		User user = new User();
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL);
			preparedStatement.setString(1, email);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user.setId(resultSet.getInt(1));
				user.setEmail(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setRole(Role.valueOf(resultSet.getString(4)));

			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);

		return user;
	}

}
