package com.volynets.edem.dao.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.dao.AbstractDao;
import com.volynets.edem.dao.CommentDao;
import com.volynets.edem.entity.Account;
import com.volynets.edem.entity.Action;
import com.volynets.edem.entity.Comment;
import com.volynets.edem.exception.DaoException;

/**
 * This class is an implementation of CommentDao.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class CommentDaoImpl extends CommentDao {
	private static final Logger LOGGER = LogManager.getLogger(CommentDaoImpl.class);

	private static final String SELECT_ALL_COMMENTS = "SELECT * FROM comment";
	private static final String SQL_FIND_COMMENT_BY_ID = "SELECT * FROM comment WHERE id=?";
	private static final String SQL_DELETE_COMMENT = "DELETE FROM comment WHERE id=?";
	private static final String SQL_UPDATE_COMMENT = "UPDATE comment "
			+ "SET `content`=?, `created`=?, `id_account`=?, `id_action`=? WHERE id=?";
    private static final String SQL_INSERT_COMMENT = "INSERT into comment "
    		+ "(`content`,`created`,`id_account`,`id_action`) VALUES (?, ?, ?, ?)";
    private static final String SQL_DELETE_COMMENT_BY_ID_ACCOUNT = "DELETE FROM comment "
    		+ "WHERE id_account=?";
    private static final String SQL_DELETE_COMMENT_BY_ID_ACTION = "DELETE FROM comment "
    		+ "WHERE id_action=?";
	private static final String SQL_FIND_COMMENTS_BY_ID_ACTION = "SELECT * FROM comment "
			+ "WHERE id_action=?";
    

	@Override
	public void insert(Comment entity) throws DaoException {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_COMMENT);
			preparedStatement.setString(1, entity.getContent());
			preparedStatement.setTimestamp(2, entity.getCreated());
			preparedStatement.setInt(3, entity.getAccount().getUser().getId());
			preparedStatement.setInt(4, entity.getAction().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
	}

	@Override
	public void update(Comment entity) throws DaoException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_UPDATE_COMMENT);
			preparedStatement.setInt(1, entity.getId());
			preparedStatement.setString(2, entity.getContent());
			preparedStatement.setTimestamp(3, entity.getCreated());
			preparedStatement.setInt(4, entity.getAccount().getId());
			preparedStatement.setInt(5, entity.getAction().getId());

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
			preparedStatement = connection.prepareStatement(SQL_DELETE_COMMENT);
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public Comment findById(int id) throws DaoException {
		AbstractDao<Account> accountDao = new AccountDaoImpl();
		accountDao.setConnection(connection);
		AbstractDao<Action> actionDao = new ActionDaoImpl();
		actionDao.setConnection(connection);
		
		Comment comment = new Comment();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_FIND_COMMENT_BY_ID);
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				comment.setId(resultSet.getInt(1));
				comment.setContent(resultSet.getString(2));
				comment.setCreated(resultSet.getTimestamp(3));
				
				int idAccount = resultSet.getInt(4);
				Account account = accountDao.findById(idAccount);
				comment.setAccount(account);
				
				int idAction = resultSet.getInt(5);
				Action action = actionDao.findById(idAction);
				comment.setAction(action);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		return comment;
	}

	@Override
	public List<Comment> findAll() throws DaoException {
		AbstractDao<Account> accountDao = new AccountDaoImpl();
		accountDao.setConnection(connection);
		AbstractDao<Action> actionDao = new ActionDaoImpl();
		actionDao.setConnection(connection);;
		
		List<Comment> comments = new ArrayList<Comment>();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_COMMENTS);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Comment comment = new Comment();

				comment.setId(resultSet.getInt(1));
				comment.setContent(resultSet.getString(2));
				comment.setCreated(resultSet.getTimestamp(3));
				
				int idAccount = resultSet.getInt(4);
				Account account = accountDao.findById(idAccount);
				comment.setAccount(account);
				
				int idAction = resultSet.getInt(5);
				Action action = actionDao.findById(idAction);
				comment.setAction(action);

				comments.add(comment);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		return comments;
	}

	@Override
	public void deleteByIdAccount(int idAccount) throws DaoException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_DELETE_COMMENT_BY_ID_ACCOUNT);
			preparedStatement.setInt(1, idAccount);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public void deleteByIdAction(int idAction) throws DaoException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_DELETE_COMMENT_BY_ID_ACTION);
			preparedStatement.setInt(1, idAction);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
		
	}

	@Override
	public List<Comment> findByActionId(int idAction) throws DaoException {
		AbstractDao<Account> accountDao = new AccountDaoImpl();
		accountDao.setConnection(connection);
		AbstractDao<Action> actionDao = new ActionDaoImpl();
		actionDao.setConnection(connection);
		
		List<Comment> comments = new ArrayList<>();
		Comment comment = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(SQL_FIND_COMMENTS_BY_ID_ACTION);
			preparedStatement.setInt(1, idAction);
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				comment = new Comment();
				comment.setId(resultSet.getInt(1));
				comment.setContent(resultSet.getString(2));
				comment.setCreated(resultSet.getTimestamp(3));
				
				int idAccount = resultSet.getInt(4);
				Account account = accountDao.findById(idAccount);
				comment.setAccount(account);
				
				Action action = actionDao.findById(idAction);
				comment.setAction(action);
				
				comments.add(comment);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		return comments;
	}
}
