package com.volynets.edem.dao.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.dao.ActionDao;
import com.volynets.edem.entity.Action;
import com.volynets.edem.exception.DaoException;

/**
 * This class is an implementation of ActionDao.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class ActionDaoImpl extends ActionDao {
	private static final Logger LOGGER = LogManager.getLogger(ActionDaoImpl.class);

	private static final String SELECT_ALL_ACTIONS = "SELECT * FROM action";
	private static final String SQL_FIND_ACTION_BY_ID = "SELECT * FROM action WHERE id=?";
	private static final String SQL_DELETE_ACTION = "DELETE FROM action WHERE id=?";
	private static final String SQL_UPDATE_ACTION = "UPDATE action "
			+ "SET `title`=?, `desc`=?, `content`=?, `logo`=?, `comment`=?, co2=? WHERE id=?";
	private static final String SQL_INSERT_ACTION = "INSERT into action "
			+ "(`title`,`desc`,`content`,`logo`,`comment`,`co2`) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_FIND_ACTION_BY_TITLE = "SELECT * FROM action WHERE title=?";
	private static final String SQL_UPDATE_ACTION_COMMENTS = "UPDATE action "
			+ "SET `comment`= ? WHERE id=?";

	@Override
	public void insert(Action entity) throws DaoException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_INSERT_ACTION);
			preparedStatement.setString(1, entity.getTitle());
			preparedStatement.setString(2, entity.getDesc());
			preparedStatement.setString(3, entity.getContent());
			preparedStatement.setString(4, entity.getLogo());
			preparedStatement.setInt(5, entity.getComments());
			preparedStatement.setInt(6, entity.getCo2());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public void update(Action entity) throws DaoException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_UPDATE_ACTION);
			preparedStatement.setString(1, entity.getTitle());
			preparedStatement.setString(2, entity.getDesc());
			preparedStatement.setString(3, entity.getContent());
			preparedStatement.setString(4, entity.getLogo());
			preparedStatement.setInt(5, entity.getComments());
			preparedStatement.setInt(6, entity.getCo2());
			preparedStatement.setInt(7, entity.getId());

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
			preparedStatement = connection.prepareStatement(SQL_DELETE_ACTION);
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public Action findById(int id) throws DaoException {
		Action action = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_FIND_ACTION_BY_ID);
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				action = new Action();
				action.setId(resultSet.getInt(1));
				action.setTitle(resultSet.getString(2));
				action.setDesc(resultSet.getString(3));
				action.setContent(resultSet.getString(4));
				action.setLogo(resultSet.getString(5));
				action.setComments(resultSet.getInt(6));
				action.setCo2(resultSet.getInt(7));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		return action;
	}

	@Override
	public List<Action> findAll() throws DaoException {
		List<Action> actions = new ArrayList<Action>();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_ACTIONS);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Action action = new Action();

				action.setId(resultSet.getInt(1));
				action.setTitle(resultSet.getString(2));
				action.setDesc(resultSet.getString(3));
				action.setContent(resultSet.getString(4));
				action.setLogo(resultSet.getString(5));
				action.setComments(resultSet.getInt(6));
				action.setCo2(resultSet.getInt(7));

				actions.add(action);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		return actions;
	}

	@Override
	public Action findByTitle(String title) throws DaoException {
		Action action = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_FIND_ACTION_BY_TITLE);
			preparedStatement.setString(1, title);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				action = new Action();
				action.setId(resultSet.getInt(1));
				action.setTitle(resultSet.getString(2));
				action.setDesc(resultSet.getString(3));
				action.setContent(resultSet.getString(4));
				action.setLogo(resultSet.getString(5));
				action.setComments(resultSet.getInt(6));
				action.setCo2(resultSet.getInt(7));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		return action;
	}
	
	@Override
	public void updateComments(int idAction, int countComments) throws DaoException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_UPDATE_ACTION_COMMENTS);
			preparedStatement.setInt(1, countComments);
			preparedStatement.setInt(2, idAction);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}
}
