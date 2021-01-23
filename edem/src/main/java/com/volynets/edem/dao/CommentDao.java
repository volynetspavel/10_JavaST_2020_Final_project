package com.volynets.edem.dao;

import java.util.List;

import com.volynets.edem.entity.Comment;
import com.volynets.edem.exception.DaoException;
import com.volynets.edem.exception.ServiceException;

/**
 * This class is a layer for interacting with comment database.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public abstract class CommentDao extends AbstractDao<Comment> {
	public abstract void deleteByIdAccount(int idAccount) throws DaoException;

	public abstract void deleteByIdAction(int idAction) throws DaoException;

	public abstract List<Comment> findByActionId(int id) throws DaoException;
}
