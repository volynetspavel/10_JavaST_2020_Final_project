package com.volynets.edem.entity;

/**
 * Animal is a entity of animal.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class Animal extends AbstractEntity {
	private String name;
	private String desc;
	private String content;
	private String logo;
	private int co2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getCo2() {
		return co2;
	}

	public void setCo2(int co2) {
		this.co2 = co2;
	}
}
