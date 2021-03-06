package com.volynets.edem.controller.command.impl.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.Command;
import com.volynets.edem.controller.command.JspPath;
import com.volynets.edem.entity.Animal;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.AnimalService;
import com.volynets.edem.service.factory.ServiceFactory;

/**
 * This class is used for adding new animal.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class AddAnimalCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(AddAnimalCommand.class);

	private static final String NAME = "name";
	private static final String DESC = "desc";
	private static final String CONTENT = "content";
	private static final String COUNT_CO2 = "count_co2";
	private static final String LOGO_PATH = "/img/animal/";
	private static final String DESTINATION = "destination";
	private static final String FILE = "file";

	private static final String RESULT = "result";
	private static final String RESULT_ERROR = "result_error";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AnimalService animalService = serviceFactory.getAnimalService();

		String name = request.getParameter(NAME);
		String desc = request.getParameter(DESC);
		String content = request.getParameter(CONTENT);
		int countCO2 = Integer.parseInt(request.getParameter(COUNT_CO2));

		final Part filePart;
		try {
			filePart = request.getPart(FILE);
		} catch (IOException | ServletException e) {
			throw new ServiceException(e);
		}

		final String fileName = getFileName(filePart);
		String logoPathForSql = LOGO_PATH + fileName;
		final String uploadPath = request.getParameter(DESTINATION);

		Animal animalFromTable = animalService.findByName(name);
		if (animalFromTable == null) {
			animalService.addAnimal(name, desc, content, logoPathForSql, countCO2);
			uploadLogo(uploadPath, filePart, fileName);

			LOGGER.debug("Animal " + name + " was successfully added.");
			request.setAttribute(RESULT, "Animal was successfully added.");
		} else {
			LOGGER.debug("Animal " + name + " has already existed.");
			request.setAttribute(RESULT_ERROR, "Animal has already existed.");
		}

		return JspPath.ADD_ANIMAL.getUrl();
	}

	private void uploadLogo(String path, Part filePart, String fileName) throws ServiceException {
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
