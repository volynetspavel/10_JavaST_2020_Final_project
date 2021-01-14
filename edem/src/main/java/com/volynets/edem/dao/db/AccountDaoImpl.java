package com.volynets.edem.dao.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.connection.ConnectionPool;
import com.volynets.edem.dao.AccountDao;
import com.volynets.edem.dao.UserDao;
import com.volynets.edem.dao.factory.DAOFactory;
import com.volynets.edem.entity.Account;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.DaoException;

/**
 * This class is an implementation of AccountDao.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class AccountDaoImpl extends AccountDao {
	private static final Logger LOGGER = LogManager.getLogger(AccountDaoImpl.class);

	private static final String SELECT_ALL_ACCOUNTS = "SELECT * FROM edem_db.account";
	private static final String SQL_FIND_ACCOUNT_BY_ID = "SELECT * FROM edem_db.account WHERE id=?";
	private static final String SQL_DELETE_ACCOUNT = "DELETE FROM edem_db.account WHERE id=?";
	private static final String SQL_UPDATE_ACCOUNT = "UPDATE edem_db.account SET `surname`=?, `name`=?, `avatar`=? WHERE id=?";
	private static final String SQL_INSERT_ACCOUNT = "INSERT into edem_db.account (`id`,`surname`,`name`,`avatar`) VALUES (?, ?, ?, ?)";

	@Override
	public void insert(Account entity) throws DaoException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_INSERT_ACCOUNT);
			preparedStatement.setInt(1, entity.getUser().getId());
			preparedStatement.setString(2, entity.getSurname());
			preparedStatement.setString(3, entity.getName());

			if (!entity.isAvatarExists()) {
				preparedStatement.setString(4, entity.getNoAvatar());
			} else {
				preparedStatement.setString(4, entity.getAvatar());
			}

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public void update(Account entity) throws DaoException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_UPDATE_ACCOUNT);
			preparedStatement.setInt(1, entity.getUser().getId());
			preparedStatement.setString(2, entity.getSurname());
			preparedStatement.setString(3, entity.getName());

			if (!entity.isAvatarExists()) {
				preparedStatement.setString(4, entity.getNoAvatar());
			} else {
				preparedStatement.setString(4, entity.getAvatar());
			}

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
			preparedStatement = connection.prepareStatement(SQL_DELETE_ACCOUNT);
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public Account findById(int id) throws DaoException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDao userDao = daoFactory.getUserDao();
		userDao.setConnection(connection);

		Account account = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_FIND_ACCOUNT_BY_ID);
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				account = new Account();
				User user = userDao.findById(id);
				account.setUser(user);

				account.setSurname(resultSet.getString(2));
				account.setName(resultSet.getString(3));

				if (resultSet.getString(4) == null) {
					account.setAvatar(account.getNoAvatar());
				} else {
					account.setAvatar(resultSet.getString(4));
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		return account;
	}

	@Override
	public List<Account> findAll() throws DaoException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDao userDao = daoFactory.getUserDao();
		userDao.setConnection(connection);

		List<Account> accounts = new ArrayList<Account>();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_ACCOUNTS);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Account account = new Account();

				User user = userDao.findById(resultSet.getInt(1));
				account.setUser(user);

				account.setSurname(resultSet.getString(2));
				account.setName(resultSet.getString(3));

				if (resultSet.getString(4) == null) {
					account.setAvatar(account.getNoAvatar());
				} else {
					account.setAvatar(resultSet.getString(4));
				}

				accounts.add(account);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		return accounts;
	}
}
