package com.volynets.edem.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.testng.annotations.Test;

import com.volynets.edem.entity.Comment;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.factory.ServiceFactory;

public class TestCommentService {
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private CommentService commentService = serviceFactory.getCommentService();
	
	private String content = "Content";
	int idAccount = 1;
	int idAction = 4; 
	
	@Test
	public void testAddNewComment() throws ServiceException {
		LocalDateTime time = LocalDateTime.now();
		Timestamp createdTime = Timestamp.valueOf(time);
		
		commentService.addNewComment(content, createdTime, idAccount, idAction);
	}
	
	@Test
	public void testFindByActionId() throws ServiceException {
		List<Comment> listComments = commentService.findByActionId(idAction);
		listComments.stream().forEach(System.out::println);
	}
}
