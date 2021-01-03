package com.volynets.edem.exception;

/**
 * DaoException is used when an exception occurred in connectionPool.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class ConnectionPoolException extends Exception {
    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
