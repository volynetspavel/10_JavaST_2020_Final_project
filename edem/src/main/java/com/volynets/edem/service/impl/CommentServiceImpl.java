package com.volynets.edem.service.impl;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.connection.ConnectionPool;
import com.volynets.edem.dao.AccountDao;
import com.volynets.edem.dao.ActionDao;
import com.volynets.edem.dao.AnimalDao;
import com.volynets.edem.dao.CommentDao;
import com.volynets.edem.dao.UserDao;
import com.volynets.edem.dao.transaction.EntityTransaction;
import com.volynets.edem.entity.Account;
import com.volynets.edem.entity.Action;
import com.volynets.edem.entity.Animal;
import com.volynets.edem.entity.Comment;
import com.volynets.edem.entity.Usage;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.DaoException;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AbstractService;
import com.volynets.edem.service.CommentService;

/**
 * This class is an implementation of CommentService.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class CommentServiceImpl extends AbstractService implements CommentService {
    private static final Logger LOGGER = LogManager.getLogger(CommentServiceImpl.class);

    private CommentDao commentDao;
    
    public CommentServiceImpl() {
        this.commentDao = daoFactory.getCommentDao();
    }

	@Override
	public List<Comment> findByActionId(int id) throws ServiceException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		commentDao.setConnection(connection);
		try {
			return commentDao.findByActionId(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}

	@Override
	public void addNewComment(String content, Timestamp createdTime, int idAccount, int idAction)
			throws ServiceException {
		EntityTransaction transaction = new EntityTransaction();
		
		AccountDao accountDao = daoFactory.getAccountDao();
		ActionDao actionDao = daoFactory.getActionDao();

		Account account = null;
		Action action = null;
		Comment comment = new Comment();

		try {
			transaction.initTransaction(accountDao, actionDao, commentDao);
			account = accountDao.findById(idAccount);
			action = actionDao.findById(idAction);
			
			comment.setContent(content);
			comment.setCreated(createdTime);
			comment.setAccount(account);
			comment.setAction(action);
			
			commentDao.insert(comment);
			
			int comments = commentDao.findByActionId(idAction).size();
			actionDao.updateComments(idAction, comments);

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
}
