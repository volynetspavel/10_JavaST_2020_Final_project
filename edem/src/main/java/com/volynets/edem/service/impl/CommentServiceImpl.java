package com.volynets.edem.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.dao.CommentDao;
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

}
