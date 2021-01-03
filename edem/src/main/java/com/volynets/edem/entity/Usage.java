package com.volynets.edem.entity;

/**
 * Usage is a entity of usage.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class Usage extends AbstractEntity {
	private Account account;
	private Action action;
	private int reducedCO2;
	private Animal animal;

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

	public int getReducedCO2() {
		return reducedCO2;
	}

	public void setReducedCO2(int reducedCO2) {
		this.reducedCO2 = reducedCO2;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
}
