package com.volynets.edem.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.volynets.edem.exception.ServiceException;

/**
 * This interface is used to process the request and response.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public interface Command {

    /**
     * This method processes the request and response.
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
    
}
