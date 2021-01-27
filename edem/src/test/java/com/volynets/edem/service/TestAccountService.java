package com.volynets.edem.service;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.volynets.edem.entity.Account;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.factory.ServiceFactory;

public class TestAccountService {
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private AccountService accountService = serviceFactory.getAccountService();

	@Test
	public void testFindAll() throws ServiceException {
		List<Account> accounts = accountService.findAll();

		// print Accounts to console
		StringBuilder stringBuilder = new StringBuilder();
		for (Account account : accounts) {
			stringBuilder.append(account.toString()).append("\n");
		}
		System.out.println(stringBuilder.toString());
	}

	@Test
	public void testDeleteAccount() throws ServiceException {
		int idAccount = 4;
		accountService.delete(idAccount);

		Account account = null;
		account = accountService.findById(idAccount);

		Assert.assertNull(account);
	}

}
