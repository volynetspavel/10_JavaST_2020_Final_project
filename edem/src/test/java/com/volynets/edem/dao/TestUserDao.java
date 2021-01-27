package com.volynets.edem.dao;

import java.sql.Connection;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.volynets.edem.connection.TestConnectionPool;
import com.volynets.edem.dao.db.UserDaoImpl;
import com.volynets.edem.dao.factory.DAOFactory;
import com.volynets.edem.entity.Role;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.DaoException;



public class TestUserDao {
	private int id = 8;
	private User user = new User();
	
	private DAOFactory daoFactory = DAOFactory.getInstance();
	private UserDao userDao = daoFactory.getUserDao();
	
	@BeforeClass
	public void launchConnection() {
		TestConnectionPool connectionPool = TestConnectionPool.getInstance();
		Connection connection = connectionPool.takeConnection();
		userDao.setConnection(connection);
	}
	
	@BeforeClass
	public void setUp() {
		String email = "user" + (++id) + "@gmail.com";
		String password = "ee11cbb19052e40b07aac0ca060c23ee";
		Role role = Role.USER;
		
	//	user.setId(id);
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);
	}
	
	@Test(description = "Check connection and equals of objects from database.")
	public void testFindById() throws DaoException {

		User resultUser = userDao.findById(id);
		Assert.assertEquals(resultUser, user);
	}
	
	@Test()
	public void testDelete() throws DaoException {
		userDao.delete(id);
	}
	
	@Test(description = "Check insert of object from database.")
	public void testInsert() throws DaoException {
		userDao.insert(user);
	}
	
	@Test()
	public void testFindAll() throws DaoException {
		List<User> users = userDao.findAll();
		
		for (User user : users) {
			System.out.println(user.toString());
		}
	}
}
