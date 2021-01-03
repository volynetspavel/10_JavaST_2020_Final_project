package com.volynets.edem.entity;

/**
 * User is a entity of user.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class User extends AbstractEntity {
	private String email;
	private String password;
	private Role role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
