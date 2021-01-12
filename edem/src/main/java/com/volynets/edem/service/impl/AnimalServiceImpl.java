package com.volynets.edem.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.dao.AnimalDao;
import com.volynets.edem.service.AbstractService;
import com.volynets.edem.service.AnimalService;

/**
 * This class is an implementation of AnimalService.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class AnimalServiceImpl extends AbstractService implements AnimalService {
    private static final Logger LOGGER = LogManager.getLogger(AnimalServiceImpl.class);

    private AnimalDao animalDao;
    
    public AnimalServiceImpl() {
        this.animalDao = daoFactory.getAnimalDao();
    }

}
