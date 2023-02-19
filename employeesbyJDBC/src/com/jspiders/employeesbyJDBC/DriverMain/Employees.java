package com.jspiders.employeesbyJDBC.DriverMain;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Employees {

	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;
	private static Properties properties;
	private static FileReader fileReader;
	private static String filePath = "D:\\WEJA1\\employeesbyJDBC\\src\\resources\\db_info.properties";
	private static String query;

	static Scanner input = new Scanner(System.in);
	static int choice;

	public static void main(String[] args) {
		try {
			fileReader = new FileReader(filePath);
			properties = new Properties();
			properties.load(fileReader);

			Class.forName(properties.getProperty("driverPath"));

			connection = DriverManager.getConnection(properties.getProperty("dburl"), properties);

			statement = connection.createStatement();

			Operations();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private static void Operations() throws Exception {
		System.out.println("which operation do you want to perform");
		System.out.println(" 1. Show Employees \n 2. Update Employee \n 3. Add Employees \n 4. Delete Employee \n 5. Exit");
		choice = input.nextInt();

		switch (choice) {
		case 1:
			showemp();
			break;
		case 2:
			updateEmp();
			break;
		case 3:
			addEmp();
			break;
		case 4:
			deleteEmp();
			break;
		case 5 : System.out.println("Exited"); exit();
			break;
		default:
			System.out.println("Give some Proper input between 1-4");
		}
		if(choice !=5) {
		Operations();
		}
	}

	private static void showemp() throws Exception {
//		
		query = "Select * from EMPLOYEES";
		try {
			resultSet = statement.executeQuery(query);
			boolean dbEmpty =true;
			
			while (resultSet.next()) {
				dbEmpty=false;
				System.out.println(resultSet.getString(1) + " || " + resultSet.getString(2) + " || "+ resultSet.getString(3) + " || " + resultSet.getString(4));
			}
			 
			if(dbEmpty) {
				 System.out.println("No Employee Found");
			 }
			
		} finally {

		}

	}
	
	private static void updateEmp() throws Exception {
		showemp();
		
		System.out.println("which employee do you want to update \n Employee no :");
		int empno = input.nextInt();
		
		String findId = "SELECT EMPNO FROM EMPLOYEES WHERE EMPNO="+empno;
		resultSet=statement.executeQuery(findId);
		
		boolean empFind= true;
		
		if(resultSet.next()) {
			empFind= false;
			
			
			System.out.println("Which field you want to Change ? \n choose from Below !!");
			System.out.println(" 1. Change Name \n 2. Change Job Title \n 3. Salary ");
			
			int field = input.nextInt();
			
			System.out.print ("Value for Updation : ");
			String parameter = input.next();
			
			switch (field) {
				case 1:
					query = "UPDATE EMPLOYEES SET EMPNAME=" + '"' + parameter + '"' + " where EMPNO=" + empno;
					statement.executeUpdate(query);
					
					showemp();
					
					System.out.println("DATABASE UPDATED");
					break;
					
				case 2 :
					query = "UPDATE EMPLOYEES SET JOB=" + '"' + parameter + '"' + " where EMPNO=" + empno;
					statement.executeUpdate(query);
					
					showemp();
					
					System.out.println("DATABASE UPDATED");
					break;
					
				case 3 :
					query = "UPDATE EMPLOYEES SET SAL=" + '"' + parameter + '"' + " where EMPNO=" + empno;
					statement.executeUpdate(query);
					
					showemp();
					
					System.out.println("DATABASE UPDATED");
					break;
				default:
					System.out.println("Enter correct Input");
					updateEmp();
					
	
			}
		}
		else {System.out.println("No Such employee found");}
		
		Operations();
	}

	private static void addEmp() throws Exception {
		
		System.out.println("How many Employees do you want to ADD ?");
		int no = input.nextInt();
		input.nextLine();
		
		int n =1;
		
		while(no>0) {
			System.out.print("Enter Name : ");
			String name = input.nextLine();
			System.out.print("Enter Job : ");
			String job = input.nextLine();
			System.out.print("Enter Salary : ");
			double salary =input.nextInt();
			input.nextLine();
			
			query= "INSERT INTO EMPLOYEES (EMPNAME , JOB, SAL) VALUES ("+ '"'+name +'"' + "," +'"'+ job +'"'+ "," + salary +" )";
			statement.executeUpdate(query);
			
			System.out.println(n +" Data Added successfully");
			
			no--;
			n++;
			
		}
		showemp();
		Operations();
	}
	
	private static void deleteEmp() throws Exception {
		showemp();
		
		System.out.println("Which Employees Record do you want to Delete ?");
		int empno = input.nextInt();
		
		String findId = "SELECT EMPNO FROM EMPLOYEES WHERE EMPNO="+empno;
		resultSet=statement.executeQuery(findId);
		
		boolean empFind= true;
		
		if(resultSet.next()) {
			empFind= false;
		
			query = "DELETE FROM EMPLOYEES WHERE EMPNO=" + empno;
			statement.executeUpdate(query);
			
			System.out.println("Record Deleted Successful");
		}
		
		else System.out.println("Employee Not Found !!");
		
		Operations();
	}
	private static void exit() throws SQLException, IOException {
		connection.close();
		statement.close();
		resultSet.close();
		fileReader.close();
	}

}
