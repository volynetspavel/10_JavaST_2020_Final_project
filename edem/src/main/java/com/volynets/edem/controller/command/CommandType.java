package com.volynets.edem.controller.command;

/**
 * This enum contains all possible command than can come from a request.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public enum CommandType {
	LANGUAGE,
	SIGN_IN,
	SIGN_OUT,
	VIEW_ACCOUNTS,
	VIEW_ACTIONS,
	VIEW_ANIMALS,
	TAKE_ACTION,
	DELETE_ACCOUNT,
	DELETE_ACTION,
	DELETE_ANIMAL,
	WATCH_ACTION,
	VISIT_REGISTRATION,
	VISIT_ADD_ACTION,
	REGISTRATION,
	TAKE_ANIMAL,
	ADD_ACTION,
	ADD_COMMENT,
	
	ADD_ANIMAL,
	UPDQTE_ACTION,
	UPDATE_ANIMAL,
	COUNT_TOTAL_CO2_BY_ACCOUNT_AND_ANIMAL;
}
