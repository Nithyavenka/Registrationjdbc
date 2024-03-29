package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtility {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Driver is registered");
	}

	public static Connection getDBConnection()throws SQLException
	{
		String url = "jdbc:mysql://localhost:3306/nithya";
		String userName = "root";
		String password = "password";
		return DriverManager.getConnection(url, userName, password);

	}

	public static void closeResource(Connection connect, Statement stmt) throws SQLException {
		if (connect != null) {
			connect.close();
			if (stmt != null)
				stmt.close();
		}

	}
	
}

