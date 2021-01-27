package com.volynets.edem.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.exception.ConnectionPoolException;

/**
 * This class is used to store, give and receive back connections.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class ConnectionPool {
	private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

	private static ConnectionPool instance;
	private static ReentrantLock lock = new ReentrantLock();
	private static AtomicBoolean create = new AtomicBoolean(false);

	private static final int MAX_POOL_SIZE;
	private static final String CONNECTION_URL;

	private BlockingQueue<PooledConnection> availableConnections;
	private Set<PooledConnection> usedConnections;

	static {
		MAX_POOL_SIZE = ConnectorDB.obtainMaxPoolSize();
		CONNECTION_URL = ConnectorDB.obtainConnectionURL();
	}

	private ConnectionPool() throws ConnectionPoolException {
		try {
			Class.forName(ConnectorDB.obtainDriver()); // register driver

			init();
		} catch (ClassNotFoundException e) {
			LOGGER.error(e);
			throw new ConnectionPoolException(e);
		}
	}

	/**
	 * This method initialize parameters of connection pool.
	 * 
	 * @throws ConnectionPoolException if connection pool does not have connections.
	 */
	private void init() throws ConnectionPoolException {
		availableConnections = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
		usedConnections = new HashSet<>(MAX_POOL_SIZE);

		for (int i = 0; i < MAX_POOL_SIZE; i++) {
			try {
				availableConnections.put(new PooledConnection(
						DriverManager.getConnection(CONNECTION_URL, ConnectorDB.obtainProperties())));
			} catch (InterruptedException | SQLException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		if (availableConnections.isEmpty()) {
			LOGGER.fatal("There is no connection in the pool.");
			throw new ConnectionPoolException("There is no connection in the pool.");
		}
	}

	public static ConnectionPool getInstance() {
		if (!create.get()) {
			try {
				lock.lock();
				if (instance == null) {
					instance = new ConnectionPool();
					create.set(true);
				}
			} catch (ConnectionPoolException e) {
				LOGGER.fatal(e.getMessage(), e);
				throw new RuntimeException(e);
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	/**
	 * This method extract connection from availableConnections and put it into
	 * usedConnections.
	 * 
	 * @return extracted connection.
	 */
	public Connection takeConnection() {

		PooledConnection connection = null;

		lock.lock();
		try {
			connection = availableConnections.take();
			usedConnections.add(connection);

			LOGGER.debug("================= Take =================");
			LOGGER.debug("Available connections = " + availableConnections.size());
			LOGGER.debug("Used connections = " + usedConnections.size());
			LOGGER.debug("MAX_POOL_SIZE = " + MAX_POOL_SIZE);

		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
			Thread.currentThread().interrupt();
		}
		lock.unlock();

		return connection;
	}

	/**
	 * This method removes connection from usedConnections and put it into
	 * availableConnections.
	 * 
	 * @param connection that should be returned to availableConnections.
	 */
	public void returnConnection(Connection connection) {
		try {
			if (connection instanceof PooledConnection) {
				PooledConnection proxyConnection = (PooledConnection) connection;
				proxyConnection.setAutoCommit(true);

				usedConnections.remove(proxyConnection);
				availableConnections.offer(proxyConnection);

				LOGGER.debug("================= Return =================");
				LOGGER.debug("Available connections = " + availableConnections.size());
				LOGGER.debug("Used connections = " + usedConnections.size());
				LOGGER.debug("MAX_POOL_SIZE = " + MAX_POOL_SIZE);
			} else {
				LOGGER.error("Connection does not belong to this pool");
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);

			try {
				connection.close();
			} catch (SQLException e1) {
				LOGGER.error(e1.getMessage(), e1);
			}
		}
	}

	/**
	 * This method close all connection from pool and deregister driver.
	 * 
	 * @throws ConnectionPoolException if problem with in correct connection closing
	 *                                 happens.
	 */
	public void closePool() throws ConnectionPoolException {
		try {
			lock.lock();
			for (int i = 0; i < MAX_POOL_SIZE; i++) {

				try {
					PooledConnection connection = availableConnections.take();
					connection.realClose();

					Enumeration<Driver> drivers = DriverManager.getDrivers();
					while (drivers.hasMoreElements()) {
						try {
							Driver driver = drivers.nextElement();
							DriverManager.deregisterDriver(driver);
						} catch (Exception e) {
							LOGGER.error(e.toString(), e);
						}
					}
				} catch (InterruptedException e) {
					LOGGER.error(e.getMessage(), e);
					Thread.currentThread().interrupt();
				} catch (SQLException e) {
					throw new ConnectionPoolException("There's a problem with connection closing.", e);
				}
			}
		} finally {
			lock.unlock();
		}
	}
}
