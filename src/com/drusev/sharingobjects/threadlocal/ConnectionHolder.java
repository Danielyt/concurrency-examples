/**
 * 
 */
package com.drusev.sharingobjects.threadlocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Drusev
 *
 */
public class ConnectionHolder {

	private static final String DB_URL = "db url";
	private static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
		@Override
		protected Connection initialValue() {
			try {
				return DriverManager.getConnection(DB_URL);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	};

	public static Connection getConnection() {
		return connectionHolder.get();
	}
}
