package com.volynets.edem.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.dao.UsageDao;
import com.volynets.edem.service.AbstractService;
import com.volynets.edem.service.UsageService;

/**
 * This class is an implementation of UsageService.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class UsageServiceImpl extends AbstractService implements UsageService {
    private static final Logger LOGGER = LogManager.getLogger(UsageServiceImpl.class);

    private UsageDao usageDao;
    
    public UsageServiceImpl() {
        this.usageDao = daoFactory.getUsageDao();
    }
}
