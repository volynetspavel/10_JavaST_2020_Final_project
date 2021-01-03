package com.volynets.edem.exception;

/**
 * DaoException is used when an exception occurred in dao.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class DaoException extends Exception {
    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}