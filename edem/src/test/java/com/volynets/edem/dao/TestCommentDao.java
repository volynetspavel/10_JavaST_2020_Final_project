package com.volynets.edem.dao;

import java.sql.Connection;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.volynets.edem.connection.TestConnectionPool;
import com.volynets.edem.dao.factory.DAOFactory;
import com.volynets.edem.entity.Comment;
import com.volynets.edem.exception.DaoException;

public class TestCommentDao {
	private DAOFactory daoFactory = DAOFactory.getInstance();
	private CommentDao commentDao = daoFactory.getCommentDao();
	
	@BeforeClass
	public void launchConnection() {
		TestConnectionPool connectionPool = TestConnectionPool.getInstance();
		Connection connection = connectionPool.takeConnection();
		commentDao.setConnection(connection);
	}
	
	@Test
	public void testFindAll() throws DaoException {
		List<Comment> comments = commentDao.findAll();
		
		comments.stream().forEach(System.out::println);
	}
}
