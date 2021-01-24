package com.volynets.edem.controller.command.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.entity.User;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.UserService;
import com.volynets.edem.service.factory.ServiceFactory;
import com.volynets.edem.validator.UserDataValidator;

/**
 * This class is used for user registration.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class RegistrationCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);

	private static final String USER = "user";
	private static final String SURNAME = "surname";
	private static final String NAME = "name";
	private static final String AVATAR = "avatar";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String CONFIRMED_PASSWORD = "confirmed_password";
	private static final String DESTINATION = "destination";
	private static final String FILE = "file";

	private static final String ERROR_EMAIL = "errorEmail";
	private static final String ERROR_PASS_AND_CONFIRMED_PASS = "errorPassAndConfirmedPassMessage";

	private static final String AVATAR_PATH = "/img/avatar/";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		String page = JspPath.ERROR.getUrl();
		boolean fail = false;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		String surname = request.getParameter(SURNAME);
		String name = request.getParameter(NAME);
		String email = request.getParameter(EMAIL);
		String password = request.getParameter(PASSWORD);
		String confirmedPassword = request.getParameter(CONFIRMED_PASSWORD);

		final Part filePart;
		try {
			filePart = request.getPart(FILE);
		} catch (IOException | ServletException e) {
			throw new ServiceException(e);
		}

		final String fileName = getFileName(filePart);
		String avatarPathForSql = AVATAR_PATH + fileName;
		final String uploadPath = request.getParameter(DESTINATION);

		UserDataValidator userDataValidator = new UserDataValidator();
		Map<String, String> errorMessages = userDataValidator
				.validateData(surname, name, avatarPathForSql, email, password, confirmedPassword);

		if (errorMessages.isEmpty()) {
			if (Objects.equals(password, confirmedPassword)) {
				if (userService.containsEmail(email)) {
					errorMessages.put(ERROR_EMAIL, "This email is taken by another account");
				}

				if (errorMessages.isEmpty()) {
					userService.registration(surname, name, avatarPathForSql, email, confirmedPassword);
					uploadAvatar(uploadPath, filePart, fileName);

					User user = userService.findByEmailAndPass(email, password).get(0);

					request.getSession().setAttribute(USER, user);

					LOGGER.info("New user registered. Email - " + user.getEmail());
					page = JspPath.HOME_USER.getUrl();
				} else {
					fail = true;
					page = JspPath.REGISTRATION.getUrl();
				}
			} else {
				fail = true;
				errorMessages.put(ERROR_PASS_AND_CONFIRMED_PASS, "Password and confirmed password do not match");
			}
		} else {
			fail = true;
		}

		if (fail) {
			request.setAttribute(SURNAME, surname);
			request.setAttribute(NAME, name);
			request.setAttribute(EMAIL, email);
			request.setAttribute(AVATAR, avatarPathForSql);

			errorMessages.forEach(request::setAttribute);

			page = JspPath.REGISTRATION.getUrl();
		}

		return page;
	}

	private void uploadAvatar(String path, Part filePart, String fileName) throws ServiceException {
		try (OutputStream out = new FileOutputStream(new File(path + File.separator + fileName));
				InputStream filecontent = filePart.getInputStream()) {

			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			LOGGER.info("New file " + fileName + " created at " + path);
		} catch (FileNotFoundException e) {
			LOGGER.error("Problems during file upload. Error: " + e);
			throw new ServiceException(e);
		} catch (IOException e) {
			throw new ServiceException(e);
		}
	}

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		LOGGER.info("Part Header = {0}", partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
