package com.volynets.edem.service;

import com.volynets.edem.dao.factory.DAOFactory;

/**
 * Basic class for service layer. Defines general data for all services.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public abstract class AbstractService {
	protected DAOFactory daoFactory = DAOFactory.getInstance();
}
