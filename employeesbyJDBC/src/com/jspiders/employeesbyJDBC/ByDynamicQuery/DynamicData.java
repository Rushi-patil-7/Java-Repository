package com.jspiders.employeesbyJDBC.ByDynamicQuery;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class DynamicData {

	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static FileReader fileReader;
	private static Properties properties;
	private static ResultSet resultSet;
	private static int result;
	private static String query;
	private static String filePath = "D:\\WEJA1\\employeesbyJDBC\\src\\resources\\db_info.properties";
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		
		try {
			fileReader = new FileReader(filePath);
			properties = new Properties();
			properties.load(fileReader);
			
			Class.forName(properties.getProperty("driverPath"));
			
			connection = DriverManager.getConnection(properties.getProperty("dburl"),properties);
			show();
			deleteQuery();
			show();
			
			
			
		} catch (IOException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(fileReader != null ) fileReader.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		

	}
	private static void updateQuery() {
		query = "UPDATE EMPLOYEES SET EMPNAME=? WHERE EMPNO =?" ;
		try {
			preparedStatement = connection.prepareStatement(query);
		
			preparedStatement.setString(1,"Prashant patil");
			
			preparedStatement.setInt(2, 12);
			
			result = preparedStatement.executeUpdate();
			
			System.out.println("Query ok " + result + " row(s) affected;");
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		
	}
	private static void show() {
		query = "Select * from EMPLOYEES";
		try {
			preparedStatement = connection.prepareStatement(query);
			boolean dbEmpty = true;
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				dbEmpty = false;
				System.out.println(resultSet.getInt(1) + " || " + resultSet.getString(2) + " || "+ resultSet.getString(3) + " || " + resultSet.getString(4));
			}
			 
			if(dbEmpty) {
				 System.out.println("No Employee Found");
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}

	}
	
	private static void insertQuery() {
		query ="insert into EMPLOYEES values (? ,? ,? ,?)";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,  sc.nextInt());
			sc.nextLine();
			preparedStatement.setString(2,sc.nextLine());
			preparedStatement.setString(3, sc.nextLine());
			preparedStatement.setDouble(4, sc.nextDouble());
			
			result = preparedStatement.executeUpdate();
			
			System.out.println("Query ok " + result + " row(s) affected");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void deleteQuery() {
		query = "DELETE FROM EMPLOYEES WHERE EMPNO =?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 13);
			result = preparedStatement.executeUpdate();
			
			System.out.println("Query ok " + result + " row(s) affected");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
