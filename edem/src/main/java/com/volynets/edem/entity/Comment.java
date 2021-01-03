package com.volynets.edem.entity;

import java.sql.Timestamp;

/**
 * Comment is a entity of comment.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class Comment extends AbstractEntity {
	private String content;
	private Timestamp created;
	private Account account;
	private Action action;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
}
