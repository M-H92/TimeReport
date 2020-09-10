package test;

import mvp.gui.FormTask;
import mvp.mqtt.sender.Subscriber;
import mvp.thread.lock.TaskMenuLock;

public class Main {

	public static TaskMenuLock test = TaskMenuLock.getTaskMenuLock();
	
	public static void main(String[] args) {
		System.out.println("Sender app is running");
		
		/*Commented block so I can get a quicker look at gui
			DO NOT DELETE
			DO NOT DELETE*/
		
		Subscriber subscriber = new Subscriber();
		try {
			subscriber.subToFeedbackTopicList();
			Main.test.setUpdating(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Starting to wait");
		
		String[] menu = Main.test.getTaskPaths();
		
		for(String menuItem : menu) {
			
		System.out.println(menuItem);
		}
		FormTask formTask = new FormTask(menu);
		
		
		/*
		String[] dummyMenu = {"1/dummy","2/dummy/subTask1","3/dummy/subTask1/subSubTask1"};
		FormTask formTask = new FormTask(dummyMenu);
		*/
		
	}

}
