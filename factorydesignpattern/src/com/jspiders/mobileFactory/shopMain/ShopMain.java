package com.jspiders.mobileFactory.shopMain;

import java.util.Scanner;

import com.jspiders.mobileFactory.shop.Iphone14pro;
import com.jspiders.mobileFactory.shop.MobileOrder;
import com.jspiders.mobileFactory.shop.Nothing1;
import com.jspiders.mobileFactory.shop.OnePlusNord2;
import com.jspiders.mobileFactory.shop.samsungs22;

public class ShopMain {

	static MobileOrder mobileorder;
	
	public static void main(String[] args) {
		
		try {
			factory().order();
		}
		catch (NullPointerException e) {
			System.out.println("No Mobile ordered");
		}
		
	}
	
	private static MobileOrder factory() {
		System.out.println("==========Choose Mobile from below=======");
		System.out.println("1. iphone 14 pro\n"
				+ "2. Oneplus Nord 2 \n"
				+ "3. Samsung s22 \n"
				+ "4. Nothing 1");
	
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		scanner.close();
		switch (choice) {
		case 1 : mobileorder = new Iphone14pro();
			return mobileorder;
		
		case 2 : mobileorder = new OnePlusNord2();
			return mobileorder;
		
		case 3 : mobileorder = new samsungs22();
			return mobileorder;
		
		case 4 : mobileorder = new Nothing1();
			return mobileorder;
			
		default : System.out.println("Invalid choice");
			return mobileorder;
			
		}
	}
	
}
