package com.volynets.edem.entity;

/**
 * Account is a entity of account.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class Account extends AbstractEntity {
	private String surname;
	private String name;
	private String avatar;

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean isAvatarExists() {
		return avatar != null;
	}

	public String getNoAvatar() {
		return "/img/avatar/no_avatar.png";
	}
}
