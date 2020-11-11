package com.app.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		
		Connection connection=null;
		try {
			//Step 1 - Load/Register the Driver
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver Loaded Successfully");
			
			
			//Step 2 - Open Connection(url,username,password)
			String url="jdbc:postgresql://localhost:5432/postgres";			
			String username="postgres";
			String password="postgres";
			connection=DriverManager.getConnection(url, username, password);
			System.out.println("Connection Successfull");
			
			//Step 3 - Create Statement
			Statement statement=connection.createStatement();
			String sql="select badgenumber,lastname from test.officers";
			System.out.println("Statement Created");
			
			//Step 4 - Execute Query
			ResultSet rs=statement.executeQuery(sql);
			System.out.println("Query Executed");
			
			//Step 5 - Process Results
			while(rs.next()) {
				System.out.print("  Badge # = "+rs.getString("badgenumber"));
				System.out.println("  Last Name = "+rs.getString("lastname"));
				//System.out.print("Id = "+rs.getInt("id"));
				//System.out.print(" Name = "+rs.getString("name"));
				//System.out.print(" Gender = "+rs.getString("gender"));
				//System.out.print(" TeamName = "+rs.getString("teamName"));
				//System.out.println(" Contact = "+rs.getLong("contact"));
			}
			System.out.println("Results Processed");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}finally {

			
			try {
				//Step 6 - Close Connection
				connection.close();
				System.out.println("Connection closed");
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}

}
