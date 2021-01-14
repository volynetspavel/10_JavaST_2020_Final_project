package com.volynets.edem.controller.command;

/**
 * This enum contains all possible command than can come from a request.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public enum CommandType {
	REGISTRATION,
	SIGN_IN, 
	SIGN_OUT,
	COUNT_TOTAL_CO2_BY_ACCOUNT_AND_ANIMAL,
	TAKE_ACTION,
	VIEW_ALL_ACCOUNTS,
	VIEW_ALL_ACTIONS,
	VIEW_ALL_ANIMALS,
	DELETE_ACCOUNT,
	DELETE_ACTION,
	DELETE_ANIMAL,
	UPDQTE_ACTION,
	UPDATE_ANIMAL;
	
}
