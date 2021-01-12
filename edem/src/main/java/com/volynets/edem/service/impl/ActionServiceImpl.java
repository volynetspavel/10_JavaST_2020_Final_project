package com.volynets.edem.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.dao.ActionDao;
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

}
