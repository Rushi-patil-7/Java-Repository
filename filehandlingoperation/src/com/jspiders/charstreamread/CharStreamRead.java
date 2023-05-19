package com.jspiders.charstreamread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CharStreamRead {

	public static void main(String[] args) {
		 File file= new File("charStreamDemo.txt");
		 if(file.exists()) {
			 try {
				 file.createNewFile();
				 System.out.println("Reading data from file");
				 FileReader fileReader = new FileReader(file);
				 System.out.println("Read () output : " + fileReader.read());
				 
				 Scanner ReadFile = new Scanner(file);
				 System.out.println( "Scanner Output ");
				 
				 while(ReadFile.hasNextLine()) {
					 System.out.println(ReadFile.nextLine());
				 }
				 
				 fileReader.close();
				 ReadFile.close();
				 
			}
			 catch (FileNotFoundException e) {
				System.out.println("File Not Found");
				e.printStackTrace();
			}
			 catch(IOException e) {
					System.out.println("File can't be created");
					e.printStackTrace();
			 }
		 }
		 else {
			 System.out.println("File not exists");
		 }
	}

	
}
