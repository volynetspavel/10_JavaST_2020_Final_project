package com.volynets.edem.service;


import java.util.List;

import com.volynets.edem.entity.Comment;
import com.volynets.edem.exception.ServiceException;

/**
 * This class is a layer for interacting with CommentDao.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public interface CommentService {
	List<Comment> findByActionId(int id) throws ServiceException;

}
