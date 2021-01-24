package com.volynets.edem.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class validates user parameters.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class UserDataValidator {
	private Map<String, String> errorMessages = new HashMap<>();

	private static final String STRING_REGEX_ALPHABETIC_STRING = "^(\\p{IsAlphabetic}+){1,50}$";
	private static final String STRING_REGEX_EMAIL = "(\\w{5,})@(\\w+\\.)([a-z]{2,4})";
	private static final String STRING_REGEX_PASSWORD = "(?=.*[\\d])(?=.*[a-z])(?=.*[A-Z]).{7,16}";

	public Map<String, String> validateData(String surname, String name, String avatar, String email, String password,
			String confirmedPassword) {

		errorMessages = validateData(surname, name, email);

		Pattern regexPassword = Pattern.compile(STRING_REGEX_PASSWORD);

		Matcher matcherPassword = regexPassword.matcher(password);
		Matcher matcherConfirmedPassword = regexPassword.matcher(confirmedPassword);

		if (!matcherPassword.matches() || !matcherConfirmedPassword.matches()) {
			errorMessages.put("errorPassword", "Password does not match the requirements");
		}

		return errorMessages;
	}

	private Map<String, String> validateData(String surname, String name, String email) {

		Pattern regexAlphabeticString = Pattern.compile(STRING_REGEX_ALPHABETIC_STRING);
		Pattern regexEmail = Pattern.compile(STRING_REGEX_EMAIL);

		Matcher matcherSurname = regexAlphabeticString.matcher(surname);
		Matcher matcherName = regexAlphabeticString.matcher(name);
		Matcher matcherEmail = regexEmail.matcher(email);

		if (!matcherSurname.matches()) {
			errorMessages.put("errorSurname", "Surname can only use letters");
		}

		if (!matcherName.matches()) {
			errorMessages.put("errorName", "Name can only use letters");
		}

		if (!matcherEmail.matches()) {
			errorMessages.put("errorEmail", "Email does not match the requirements");
		}

		return errorMessages;
	}

	private boolean validatePass(String password) {
		Pattern regexPassword = Pattern.compile(STRING_REGEX_PASSWORD);
		Matcher matcherPassword = regexPassword.matcher(password);

		return matcherPassword.matches();
	}
}
