 package com.jspiders.charstreamwrite;

import java.io.File;
import java.io.FileWriter;

public class CharStreamWrite {
	
	public static void main(String[] args) {
		 File file= new File("d:/charStreamDemo.txt");
		 if(file.exists()) {
			 System.out.println("File already exists");
			 file.delete();
		 }
		 else {
			 try {
				 file.createNewFile();
				 System.out.println("File created");
				 FileWriter fileWriter = new FileWriter(file);
				 fileWriter.write("Hiii i am Rushikesh Masule \n create this file from char stream demo");
				 fileWriter.close();
			} catch (Exception e) {
				System.out.println("File can't be created");
				e.printStackTrace();
			}
		 }
	}
	
}
