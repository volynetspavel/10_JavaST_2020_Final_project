package com.volynets.edem.dao;

import java.sql.Connection;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.volynets.edem.connection.TestConnectionPool;
import com.volynets.edem.dao.db.UsageDaoImpl;
import com.volynets.edem.dao.factory.DAOFactory;
import com.volynets.edem.exception.DaoException;
import com.volynets.edem.entity.Account;
import com.volynets.edem.entity.Action;
import com.volynets.edem.entity.Animal;
import com.volynets.edem.entity.Role;
import com.volynets.edem.entity.Usage;
import com.volynets.edem.entity.User;

public class TestUsageDao {
	private int idUsage = 10;
	private Usage usage = new Usage();
	private Account account = new Account();
	private User user = new User();
	private Animal animal = new Animal();
	private Action action = new Action();

	private Usage usageForUpdate = new Usage();
	
	private DAOFactory daoFactory = DAOFactory.getInstance();
	private UsageDao usageDao = daoFactory.getUsageDao();

	@BeforeClass
	public void launchConnection() {
		TestConnectionPool connectionPool = TestConnectionPool.getInstance();
		Connection connection = connectionPool.takeConnection();
		usageDao.setConnection(connection);
	}

	@BeforeClass
	public void setUpUser() {
		int id = 1;
		String email = "user1@gmail.com";
		String password = "ee11cbb19052e40b07aac0ca060c23ee";
		Role role = Role.USER;

		user.setId(id);
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);
	}

	@BeforeClass
	public void setUpAccount() {
		String surname = "Petrov";
		String name = "Dmitriy";
		String avatar = "/img/avatar/Petrov.jpg";

		account.setUser(user);
		account.setSurname(surname);
		account.setName(name);
		account.setAvatar(avatar);
	}

	@BeforeClass
	public void setUpAction() {
		int id = 2;
		String title = "Reusable Bags";
		String desc = "Before venturing out on your next shopping trip, make sure you�ve some reusable shopping bags with you.";
		String content = "Lightweight reusable bags can fit in a purse or pocket, and they reduces waste, "
				+ "eliminate the energy and materials needed to make disposable packaging, and save money "
				+ "in areas where laws require retailers to charge customers extra for each bag.";
		String logo = "/img/action/Reusable-Bags.jpg";
		int comments = 1;
		int co2 = 5;
		
		action.setId(id);
		action.setTitle(title);
		action.setDesc(desc);
		action.setContent(content);
		action.setLogo(logo);
		action.setComments(comments);
		action.setCo2(co2);
	}

	@BeforeClass
	public void setUpAnimal() {
		int id = 1;
		String name = "Polar bear";
		String desc = "Polar bears live in Alaska, Canada, Russia, Greenland, "
				+ "and some northern islands owned by Norway, such as Svalbard. "
				+ "Polar bears depend on the sea ice, which forms above the open "
				+ "waters where their seal prey lives.";
		String content = "Hunting of these bears caused a steep drop in populations"
				+ " in the 20th century. Each year the number of bears killed increased,"
				+ " as hunting technology grew more efficient. Hunting hit its peak in"
				+ " 1968 with over 1,250 bears killed. Hunting regulations helped to"
				+ " improve their population numbers.";
		String logo = "/img/animal/Polar-Bear-5-650x425.jpg";
		int co2 = 1500;

		animal.setId(id);
		animal.setName(name);
		animal.setDesc(desc);
		animal.setContent(content);
		animal.setLogo(logo);
		animal.setCo2(co2);
	}

	@BeforeClass
	public void setUpUsage() {
		int co2 = 5;
		
		usage.setId(idUsage);
		usage.setAccount(account);
		usage.setAction(action);
		usage.setReducedCO2(co2);
		usage.setAnimal(animal);
	}
	
	@BeforeClass
	public void setUpUsageForUpdate() {
		int co2 = 10;
		
		usageForUpdate.setId(idUsage);
		usageForUpdate.setAccount(account);
		usageForUpdate.setAction(action);
		usageForUpdate.setReducedCO2(co2);
		usageForUpdate.setAnimal(animal);
	}

	@Test(description = "Check update of object from database.")
	public void testUpdate() throws DaoException {
		usageDao.update(usageForUpdate);
	}
	
	@Test(description = "Check delete of object from database.",
			groups = "delete, insert, findById")
	public void testDelete() throws DaoException {
		usageDao.delete(idUsage);
	}

	@Test(description = "Check insert of object from database.",
			priority = 1, dependsOnMethods = "testDelete",
			groups = "delete, insert, findById")
	public void testInsert() throws DaoException {
		usageDao.insert(usage);
	}
	
	@Test(description = "Check connection and equals of objects from database.",
			priority = 2, dependsOnMethods = "testInsert",
			groups = "delete, insert, findById")
	public void testFindById() throws DaoException {
		//id is autoincrement and after delete it is changing in db
		idUsage++;
		usage.setId(idUsage);
		Usage resultUsage = usageDao.findById(idUsage);
		
		Assert.assertEquals(usage, resultUsage);
	}
}
