package com.bank.dao.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection {

	private PostgreSQLConnection() {

		// TODO Auto-generated constructor stub
	}

	private static Connection connection;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName(ConnectionURLDriver.DRIVER); //DRIVER = jdbc:postgresql://localhost:5432/postgres
		String url=ConnectionURLDriver.URL; // URL = org.postgresql.Driver
		
		 String username=System.getenv("postgresUserName"); 
		 String password=System.getenv("postgresPassword");
		 
		 connection = DriverManager.getConnection(url, username, password);
		 return connection;
		
	}
}
