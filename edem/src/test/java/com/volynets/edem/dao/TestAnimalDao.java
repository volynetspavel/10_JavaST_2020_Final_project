package com.volynets.edem.dao;

import java.sql.Connection;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.volynets.edem.connection.TestConnectionPool;
import com.volynets.edem.dao.db.AnimalDaoImpl;
import com.volynets.edem.dao.factory.DAOFactory;
import com.volynets.edem.entity.Animal;
import com.volynets.edem.exception.DaoException;

public class TestAnimalDao {
	private int idAnimal = 8;
	
	private DAOFactory daoFactory = DAOFactory.getInstance();
	private AbstractDao<Animal> animalDao = daoFactory.getAnimalDao();
	
	private Animal animal = new Animal();
	private Animal animalForUpdate = new Animal();
	
	@BeforeClass
	public void launchConnection() {
		TestConnectionPool connectionPool = TestConnectionPool.getInstance();
		Connection connection = connectionPool.takeConnection();
		animalDao.setConnection(connection);
	}
	
	@BeforeClass
	public void setUp() {
		String name = "Wolverine";
		String desc = "They are powerfully built and have short legs with wide feet for traveling across the snow. These animals are a vital part of ecosystems in northern climes, and a great ambassador of the wild places they inhabit and the melting snow they require.";
		String content = "Climate change is in impending threat to these mustelids. Prior to giving birth, females will create dens in the snow banks and rely on these dens until the young are weaned. Changing climate is altering the range of these animals because the snow is melting earlier and earlier in the season. These creatures also suffer from encroaching human development cutting into their habitat.\r\n"
				+ " Sadly, wolverines are still trapped and killed for their fur and to reduce their numbers in certain areas. Their habit of eating anything they can get their paws on makes them considered a nuisance by most farmers.";
		String logo = "/img/animal/Wolverine-6-650x425.jpg";
		int co2 = 1100;
		
		animal.setId(idAnimal);
		animal.setName(name);
		animal.setDesc(desc);
		animal.setContent(content);
		animal.setLogo(logo);
		animal.setCo2(co2);
	}
	
	@BeforeClass
	public void setUpAnimalForUpdate() {
		int id = 7;
		String name = "Wolverine";
		String desc = "Hello! Polar bears live";
		String content = "Climate";
		String logo = "/img/an";
		int co2 = 13300;

		animalForUpdate.setId(id);
		animalForUpdate.setName(name);
		animalForUpdate.setDesc(desc);
		animalForUpdate.setContent(content);
		animalForUpdate.setLogo(logo);
		animalForUpdate.setCo2(co2);
	}
	
	@Test(description = "Check update of object from database.")
	public void testUpdate() throws DaoException {
		animalDao.update(animalForUpdate);
	}
	
	@Test(description = "Check delete of object from database.",
			groups = "delete, insert, findById")
	public void testDelete() throws DaoException {
		animalDao.delete(idAnimal);
	}
	
	@Test(description = "Check insert of object from database.",
			priority = 1, dependsOnMethods = "testDelete",
			groups = "delete, insert, findById")
	public void testInsert() throws DaoException {
		animalDao.insert(animal);
	}
	
	@Test(description = "Check connection and equals of objects from database.",
			priority = 2, dependsOnMethods = "testInsert",
			groups = "delete, insert, findById")
	public void testFindById() throws DaoException {
		//id is autoincrement and after delete it is changing in db
		idAnimal++;
		animal.setId(idAnimal);
		Animal resultAnimal = animalDao.findById(idAnimal);
		
		Assert.assertEquals(animal, resultAnimal);
	}
	
	@Test()
	public void testFindAll() throws DaoException {
		List<Animal> animals = animalDao.findAll();
		
		for (Animal animal : animals) {
			System.out.println(animal.toString());
		}
	}
}
