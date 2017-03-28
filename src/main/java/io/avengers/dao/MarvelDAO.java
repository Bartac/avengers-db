package io.avengers.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MarvelDAO {

	static Class c;
	
	public MarvelDAO() {
		if (c == null) {
			try {
				c = Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException("SQL driver is not here: " + e.getMessage());
			}
		}
	}
	
	Connection connectToMySQL() {
		Connection connect;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/marvel", "root", "");
			return connect;
		} catch (SQLException e) {
			throw new IllegalStateException("Wrong credentials or url, or overloaded connection: " + e.getMessage());
		}
	}
}
