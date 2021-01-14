package com.volynets.edem.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.connection.ConnectionPool;
import com.volynets.edem.dao.ActionDao;
import com.volynets.edem.dao.ActionDao;
import com.volynets.edem.dao.CommentDao;
import com.volynets.edem.dao.UsageDao;
import com.volynets.edem.dao.UserDao;
import com.volynets.edem.dao.transaction.EntityTransaction;
import com.volynets.edem.entity.Action;
import com.volynets.edem.exception.DaoException;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AbstractService;
import com.volynets.edem.service.ActionService;

/**
 * This class is an implementation of ActionService.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class ActionServiceImpl extends AbstractService implements ActionService {
	private static final Logger LOGGER = LogManager.getLogger(ActionServiceImpl.class);

	private ActionDao actionDao;

	public ActionServiceImpl() {
		this.actionDao = daoFactory.getActionDao();
	}

	@Override
	public List<Action> findAll() throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		actionDao.setConnection(connection);
		try {
			return actionDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}

	@Override
	public void delete(int idAction) throws ServiceException {
		EntityTransaction transaction = new EntityTransaction();

		UsageDao usageDao = daoFactory.getUsageDao();
		CommentDao commentDao = daoFactory.getCommentDao();

		try {
			transaction.initTransaction(usageDao, commentDao, actionDao);
			usageDao.deleteByIdAction(idAction);
			commentDao.deleteByIdAction(idAction);
			actionDao.delete(idAction);

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
	public Action findById(int id) throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		actionDao.setConnection(connection);
		try {
			return actionDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}

	@Override
	public void update(Action action) throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		actionDao.setConnection(connection);
		try {
			actionDao.update(action);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}

}
