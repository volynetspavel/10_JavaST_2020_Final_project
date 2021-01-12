package com.volynets.edem.dao.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.dao.AbstractDao;
import com.volynets.edem.dao.UsageDao;
import com.volynets.edem.entity.Account;
import com.volynets.edem.entity.Action;
import com.volynets.edem.entity.Animal;
import com.volynets.edem.entity.Usage;
import com.volynets.edem.exception.DaoException;

/**
 * This class is an implementation of UsageDao.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class UsageDaoImpl extends UsageDao {
	private static final Logger LOGGER = LogManager.getLogger(UsageDaoImpl.class);

	private static final String SELECT_ALL_USAGES = "SELECT * FROM usage";
	private static final String SQL_FIND_USAGE_BY_ID = "SELECT * FROM edem_db.usage WHERE id=?";
	private static final String SQL_DELETE_USAGE = "DELETE FROM edem_db.usage WHERE id=?";
	private static final String SQL_UPDATE_USAGE = "UPDATE edem_db.usage "
			+ "SET id_account=?, id_action=?, reducedCO2=?, id_animal=? WHERE id=?";
    private static final String SQL_INSERT_USAGE = "INSERT into edem_db.usage "
    		+ "(id_account, id_action, reducedCO2, id_animal) VALUES (?, ?, ?, ?)";

	@Override
	public void insert(Usage entity) throws DaoException {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_USAGE);
			preparedStatement.setInt(1, entity.getAccount().getUser().getId());
			preparedStatement.setInt(2, entity.getAction().getId());
			preparedStatement.setInt(3, entity.getReducedCO2());
			preparedStatement.setInt(4, entity.getAnimal().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
	}

	@Override
	public void update(Usage entity) throws DaoException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_UPDATE_USAGE);
			preparedStatement.setInt(1, entity.getId());
			preparedStatement.setInt(2, entity.getAccount().getUser().getId());
			preparedStatement.setInt(3, entity.getAction().getId());
			preparedStatement.setInt(4, entity.getReducedCO2());
			preparedStatement.setInt(5, entity.getAnimal().getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public void delete(int id) throws DaoException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_DELETE_USAGE);
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public Usage findById(int id) throws DaoException {
		AbstractDao<Account> accountDao = new AccountDaoImpl();
		accountDao.setConnection(connection);
		AbstractDao<Action> actionDao = new ActionDaoImpl();
		actionDao.setConnection(connection);
		AbstractDao<Animal> animalDao = new AnimalDaoImpl();
		animalDao.setConnection(connection);
		
		Usage usage = new Usage();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_FIND_USAGE_BY_ID);
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				usage.setId(resultSet.getInt(1));
				
				int idAccount = resultSet.getInt(2);
				Account account = accountDao.findById(idAccount);
				usage.setAccount(account);
				
				int idAction = resultSet.getInt(3);
				Action action = actionDao.findById(idAction);
				usage.setAction(action);
				
				usage.setReducedCO2(resultSet.getInt(4));
				
				int idAnimal = resultSet.getInt(5);
				Animal animal = animalDao.findById(idAnimal);
				usage.setAnimal(animal);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		return usage;
	}

	@Override
	public List<Usage> findAll() throws DaoException {
		AbstractDao<Account> accountDao = new AccountDaoImpl();
		accountDao.setConnection(connection);
		AbstractDao<Action> actionDao = new ActionDaoImpl();
		actionDao.setConnection(connection);
		AbstractDao<Animal> animalDao = new AnimalDaoImpl();
		animalDao.setConnection(connection);
		
		List<Usage> usages = new ArrayList<Usage>();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_USAGES);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Usage usage = new Usage();

				usage.setId(resultSet.getInt(1));
				
				int idAccount = resultSet.getInt(2);
				Account account = accountDao.findById(idAccount);
				usage.setAccount(account);
				
				int idAction = resultSet.getInt(3);
				Action action = actionDao.findById(idAction);
				usage.setAction(action);
				
				usage.setReducedCO2(resultSet.getInt(4));
				
				int idAnimal = resultSet.getInt(5);
				Animal animal = animalDao.findById(idAnimal);
				usage.setAnimal(animal);

				usages.add(usage);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		return usages;
	}
}
