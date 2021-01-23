package com.volynets.edem.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.connection.ConnectionPool;
import com.volynets.edem.dao.CommentDao;
import com.volynets.edem.entity.Comment;
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

}
