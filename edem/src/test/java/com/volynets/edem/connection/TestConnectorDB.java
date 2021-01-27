package com.volynets.edem.connection;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * This class is used to store, give and receive back connections.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class TestConnectorDB {
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("test");
	
    private static final String USER_NAME = resourceBundle.getString("db.user");
    private static final String USER_PASSWORD = resourceBundle.getString("db.password");

    private static final String DB_DRIVER = resourceBundle.getString("db.driver");
    private static final String CONNECTION_URL = resourceBundle.getString("db.url");

    private static final String DB_USE_UNICODE = resourceBundle.getString("db.useUnicode");
    private static final String DB_ENCODING = resourceBundle.getString("db.encoding");

    private static final String MAX_POOL_SIZE = resourceBundle.getString("db.maxPoolSize");
    
    public static Properties obtainProperties() {
		Properties properties = new Properties();
		properties.put("user", USER_NAME);
		properties.put("password", USER_PASSWORD);
		properties.put("useUnicode", DB_USE_UNICODE);
		properties.put("characterEncoding", DB_ENCODING);

		return properties;
	}
	
	static String obtainDriver() {
		return DB_DRIVER;
	}

	static int obtainMaxPoolSize() {
		return Integer.parseInt(MAX_POOL_SIZE);
	}

	static String obtainConnectionURL() {
		return CONNECTION_URL;
	}
}
