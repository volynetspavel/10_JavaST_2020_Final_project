package com.volynets.edem.dao.factory;

import com.volynets.edem.dao.AccountDao;
import com.volynets.edem.dao.ActionDao;
import com.volynets.edem.dao.AnimalDao;
import com.volynets.edem.dao.CommentDao;
import com.volynets.edem.dao.UsageDao;
import com.volynets.edem.dao.UserDao;
import com.volynets.edem.dao.db.AccountDaoImpl;
import com.volynets.edem.dao.db.ActionDaoImpl;
import com.volynets.edem.dao.db.AnimalDaoImpl;
import com.volynets.edem.dao.db.CommentDaoImpl;
import com.volynets.edem.dao.db.UsageDaoImpl;
import com.volynets.edem.dao.db.UserDaoImpl;

/**
 * Factory for creating dao-objects.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public final class DAOFactory {
	private static final DAOFactory INSTANCE = new DAOFactory();

	private final AccountDao accountDao = new AccountDaoImpl();
	private final ActionDao actionDao = new ActionDaoImpl();
	private final AnimalDao animalDao = new AnimalDaoImpl();
	private final CommentDao commentDao = new CommentDaoImpl();
	private final UsageDao usageDao = new UsageDaoImpl();
	private final UserDao userDao = new UserDaoImpl();

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return INSTANCE;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public ActionDao getActionDao() {
		return actionDao;
	}

	public AnimalDao getAnimalDao() {
		return animalDao;
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public UsageDao getUsageDao() {
		return usageDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}
}
