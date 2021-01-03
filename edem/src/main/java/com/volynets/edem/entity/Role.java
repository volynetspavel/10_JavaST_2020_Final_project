package com.volynets.edem.entity;

/**
 * Role is a enum of different user types.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public enum Role {
	ADMINISTRATOR("Administrator"),
	USER("User");

	private String name;

	private Role(String name) {
		this.name = name;
	}

	public String getRole() {
		return name;
	}
}
