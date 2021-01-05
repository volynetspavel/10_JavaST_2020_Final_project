package com.volynets.edem.entity;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(account, action, reducedCO2, animal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usage other = (Usage) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (animal == null) {
			if (other.animal != null)
				return false;
		} else if (!animal.equals(other.animal))
			return false;
		if (reducedCO2 != other.reducedCO2)
			return false;
		return true;
	}
}
