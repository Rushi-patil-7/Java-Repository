package com.jspider.timer.main;

import com.jspider.timer.thread.Alarm;
import com.jspider.timer.thread.RunTimer;

public class MyTimer {

	public static void main(String[] args) {
		RunTimer runTimer =new RunTimer();
		Alarm alarm = new Alarm();
		runTimer.run();
		alarm.timeUP();
	}
}
