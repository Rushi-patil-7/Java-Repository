package com.jspiders.musicplayerbyJDBC.maindriver;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class MainPlayer {

	private static Connection con;
	private static Statement st;
	private static ResultSet rs;
	private static FileReader fr;
	private static String filePath = "D:\\WEJA1\\employeesbyJDBC\\src\\resources\\db_info.properties";
	private static Properties prts;
	private static String query;
	
	static Scanner input = new Scanner(System.in);
	static int choice;
	public static void main(String[] args) {
		
		try {
				fr = new FileReader(filePath);
				prts = new Properties();
				prts.load(fr);
			
				Class.forName(prts.getProperty("driverPath"));
				
				con= DriverManager.getConnection(prts.getProperty("dburl"), prts);
			
				st = con.createStatement();
				operations();
		}	
		catch (IOException  | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	private static void operations() {
		System.out.println("========Welcome to Music Player ==========");
		System.out.println("Choose Operation from Below !!");
		System.out.println("1. Show All Songs \n 2. Insert Song \n 3. Update Song \n 4. Delete Song \n 5.Exit");
		int choice = input.nextInt();
		
		switch(choice) {
		case 1 : showList();
				break;
		case 2 : addSongs();
				break;
		case 3 : updateSongs();
				break;
		case 4 : deleteSongs();
				break;
		case 5 : System.out.println("Exitted");
			try {
				exit();
			
			} catch (IOException |SQLException  e) {
				e.printStackTrace();
			}
				break;
		default : System.out.println("Give Proper input between 1 to 5 ");
		}
	}
	private static void showList() {
		query = "SELECT * FROM SONGS";
		
		try {
			rs = st.executeQuery(query);
			boolean isEmpty= true;
			
			while(rs.next()) {
				isEmpty= false;
				System.out.println(rs.getInt(1) + " | " + rs.getString(2) + "|" + rs.getString(3) + "|" + rs.getString(4) + "|" +rs.getString(5));
			}
			if(isEmpty) {
				System.out.println("No songs Added !!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	private static void addSongs() {
		System.out.println("How Many songs do you want to ADD in Library ?");
		choice =input.nextInt();
		input.nextLine();
		int no=1;
		while(choice>0) {
			System.out.println("Song Name :");
			String songName = input.nextLine();
			
			System.out.println("Song Diration :");
			float time = input.nextFloat();
			input.nextLine();
			
			System.out.println("Movie Name :");
			String movie = input.nextLine();
			
			System.out.println("Singer Name :");
			String singer = input.nextLine();
	
			query = "INSERT INTO SONGS (SONGNAME , LENGTH, MOVIE, SINGER) VALUES ("+"'"+ songName + "'" + ","+time + "," +"'"+ movie +"'" + ","+"'" +singer +"'" +")";
			try {
				st.executeUpdate(query);
				
				System.out.println( no+ " Song Added Successfully");
				choice--;
				no++;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		operations();
	}
	private static void updateSongs() {
		showList();
		System.out.println("Which song do you want to update");
		int songId = input.nextInt();
		System.out.println("Which Field do you want to Update Choose from Below !!");
		System.out.println(" 1. change Name \n 2. Change Time \n 3.Change Movie \n 4. Change Singer ");
		choice = input. nextInt(); 
		input.nextLine();
		
		
		switch (choice) {
		case 1 : 
			System.out.print("Enter Song Name : ");
			String sname= input.nextLine();
			query = "UPDATE SONGS SET SONGNAME = " +"'" +sname +"'" +  " WHERE ID =" + songId;
			
			try {
				st.executeLargeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			break;
		
		case 2 : 
			System.out.print("Enter Duration : ");
			float length= input.nextFloat();
			query = "UPDATE SONGS SET LENGTH = " +"'" +length +"'" +  " WHERE ID =" + songId;
			
			try {
				st.executeLargeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			break;
		
		case 3 :
			System.out.print("Enter Movie Name : ");
			String movie= input.nextLine();
			query = "UPDATE SONGS SET MOVIE = " +"'" +movie +"'" +  " WHERE ID =" + songId;
			
			try {
				st.executeLargeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			break;
		
		case 4 :
			System.out.print("Enter Singer Name : ");
			String singer= input.nextLine();
			query = "UPDATE SONGS SET SINGER = " +"'" +singer +"'" +  " WHERE ID =" + songId;
			
			try {
				st.executeLargeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			break;
		
		default : System.out.println("Choose Proper input from 1 to 4");
		updateSongs();
		}
		System.out.println("Song Updated Successfully");
		operations();
	}
	private static void deleteSongs() {
		showList();
		System.out.println("Which song do you want to Delete ");
		int choice = input.nextInt();
		
		
		query = "DELETE FROM SONGS WHERE ID="+ choice;
		
		try {
			st.executeUpdate(query);
			System.out.println("Song Delete Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		operations();
	}
	
	private static void exit() throws SQLException, IOException {
		con.close();
		st.close();
		rs.close();
		fr.close();
	}
}
