package com.jspiders.employeesbyJDBC.StoreProcedure;

	import java.sql.CallableStatement;
	import java.io.FileReader;
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Properties;
	import java.util.Scanner;

	public class StoreProcedure {

		private static CallableStatement callableStatement;
		private static Connection connection;
		private static FileReader fileReader;
		private static Properties properties;
		private static ResultSet resultSet;
		private static String query;
		private static String filePath = "D:\\WEJA1\\employeesbyJDBC\\src\\resources\\db_info.properties";
		private static Scanner sc = new Scanner(System.in);
		
		public static void main(String[] args) {
			
			System.out.println("rushi");
			try {
				fileReader = new FileReader(filePath);
				properties = new Properties();
				properties.load(fileReader);
				
				Class.forName(properties.getProperty("driverPath"));
				
				connection = DriverManager.getConnection(properties.getProperty("dburl"),properties);
				
				query = "call proc1()";
				
				callableStatement = connection.prepareCall(query);
				resultSet = callableStatement.executeQuery ();
				
				while(resultSet.next()) {
					System.out.println(resultSet.getString(1) + " || "
							+  resultSet.getString(2)   + " || "
							+  resultSet.getString(3)	+ " || "
							+  resultSet.getString(4));
				}
				
			} catch (IOException | SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(connection != null) connection.close();
					if(fileReader != null ) fileReader.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			

		}
		
	}

