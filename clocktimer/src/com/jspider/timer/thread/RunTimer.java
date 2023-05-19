package com.jspider.timer.thread;

import java.util.Scanner;

public class RunTimer {
	int minutes;
	int seconds;

	public void run() {
		System.out.println("Set Timer !");
		Scanner userInput = new Scanner(System.in);
		System.out.print("Minutes : ");
		minutes = userInput.nextInt();
		System.out.print("Seconds : ");
		
		int seconds = userInput.nextInt();
		System.out.println("Timer Set For " + minutes + " Minutes and " + seconds + " Seconds.");
		for (int i = minutes; i >= 0 && i <= 60; i--) {
			for (int j = seconds; j <= 59 && j >= 0; j--) {
				
				try {
					
					if(i < 10 && j < 10) System.out.println("0" + i + " : " + "0" + j);	
					
					else if( i > 10 && j < 10) System.out.println(  i + " : " +"0" + j); 
					
					else if(i < 10 && j >= 10) System.out.println( "0" + i + " : " + j); 
					
					else System.out.println( i + " : " +j); 
					 
					Thread.sleep(100);
				} 
				catch (InterruptedException e) {

					e.printStackTrace();
				}

			}
			seconds=59;
		}

	}
}
