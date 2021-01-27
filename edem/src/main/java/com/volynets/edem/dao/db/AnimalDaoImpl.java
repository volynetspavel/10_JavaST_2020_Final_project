package com.volynets.edem.dao.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.dao.AnimalDao;
import com.volynets.edem.entity.Animal;
import com.volynets.edem.exception.DaoException;
import com.volynets.edem.exception.ServiceException;

/**
 * This class is an implementation of AnimalDao.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class AnimalDaoImpl extends AnimalDao {
	private static final Logger LOGGER = LogManager.getLogger(AnimalDaoImpl.class);

	private static final String SELECT_ALL_ANIMALS = "SELECT * FROM animal";
	private static final String SQL_FIND_ANIMAL_BY_ID = "SELECT * FROM animal WHERE id=?";
	private static final String SQL_DELETE_ANIMAL = "DELETE FROM animal WHERE id=?";
	private static final String SQL_UPDATE_ANIMAL = "UPDATE animal "
			+ "SET `name`=?, `desc`=?, `content`=?, `logo`=?, `co2`=? WHERE id=?";
	private static final String SQL_INSERT_ANIMAL = "INSERT INTO animal "
			+ "(`name`,`desc`,`content`,`logo`,`co2`) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_FIND_ANIMAL_BY_NAME = "SELECT * FROM animal WHERE name=?";

	@Override
	public void insert(Animal entity) throws DaoException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_INSERT_ANIMAL);
			preparedStatement.setString(1, entity.getName());
			preparedStatement.setString(2, entity.getDesc());
			preparedStatement.setString(3, entity.getContent());
			preparedStatement.setString(4, entity.getLogo());
			preparedStatement.setInt(5, entity.getCo2());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public void update(Animal entity) throws DaoException {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_UPDATE_ANIMAL);
			preparedStatement.setString(1, entity.getName());
			preparedStatement.setString(2, entity.getDesc());
			preparedStatement.setString(3, entity.getContent());
			preparedStatement.setString(4, entity.getLogo());
			preparedStatement.setInt(5, entity.getCo2());
			preparedStatement.setInt(6, entity.getId());

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
			preparedStatement = connection.prepareStatement(SQL_DELETE_ANIMAL);
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public Animal findById(int id) throws DaoException {
		Animal animal = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_FIND_ANIMAL_BY_ID);
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				animal = new Animal();
				animal.setId(resultSet.getInt(1));
				animal.setName(resultSet.getString(2));
				animal.setDesc(resultSet.getString(3));
				animal.setContent(resultSet.getString(4));
				animal.setLogo(resultSet.getString(5));
				animal.setCo2(resultSet.getInt(6));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		return animal;
	}

	@Override
	public List<Animal> findAll() throws DaoException {
		List<Animal> animals = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL_ANIMALS);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Animal animal = new Animal();

				animal.setId(resultSet.getInt(1));
				animal.setName(resultSet.getString(2));
				animal.setDesc(resultSet.getString(3));
				animal.setContent(resultSet.getString(4));
				animal.setLogo(resultSet.getString(5));
				animal.setCo2(resultSet.getInt(6));

				animals.add(animal);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		return animals;
	}

	@Override
	public Animal findByName(String name) throws DaoException {
		Animal animal = new Animal();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SQL_FIND_ANIMAL_BY_NAME);
			preparedStatement.setString(1, name);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				animal.setId(resultSet.getInt(1));
				animal.setName(resultSet.getString(2));
				animal.setDesc(resultSet.getString(3));
				animal.setContent(resultSet.getString(4));
				animal.setLogo(resultSet.getString(5));
				animal.setCo2(resultSet.getInt(6));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		closeResultSet(resultSet);
		closePreparedStatement(preparedStatement);
		return animal;
	}
}
