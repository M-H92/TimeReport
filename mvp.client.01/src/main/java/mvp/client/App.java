package mvp.client;

import javax.swing.JOptionPane;

import mvp.gui.FormTask;
import mvp.mqtt.sender.Subscriber;
import mvp.thread.lock.TaskMenuLock;

public class App {
	
	/*
	 * Static fields
	 */
	private static TaskMenuLock taskMenuLock = TaskMenuLock.getTaskMenuLock();
	
	
	
	/*
	 * Fields
	 */
	private String idMQTT = "";
	private String askTaskMessage = "";
	private Subscriber subscriber;
	private int callBeforeTimeout;
	
	
	
	/*
	 * Static get/set 
	 */
	//TODO Isn't that a singleton allready? I could ask for that with TaskMenuLock.getTaskMenuLock(). Check that later
	//Yes, it is. I have better tweaking/fixing to do, though
	static public TaskMenuLock getTaskMenuLock() {
		return App.taskMenuLock;
	}
	
	
	
	/*
	 * Get/Set
	 */
	public String getIdMQTT() {
		return idMQTT;
	}
	
	public String getAskTaskMessage() {
		return this.askTaskMessage;
	}
	
	
	
	/*
	 * Ctor
	 */
	/**
	 * Default setup. Multiple mvp client running on this settup will screw things up.
	 * Asking for the full task list may lead to an oversized MQTT message.
	 * Thus, getting corrupted data
	 * Try the keyword ACTIVE if you want to minimize risks
	 */
	public App() {
		this.setUp("cli_01","FULL");
	}

	/**
	 * Asking for the full task list may lead to an oversized MQTT message.
	 * Thus, getting corrupted data.
	 * Try the keyword ACTIVE if you want to minimize risks
	 */
	public App(String idMQTT) {
		this.setUp(idMQTT, "FULL");
	}
	
	public App(String idMQTT, String askTaskMessage) {
		this.setUp(idMQTT, askTaskMessage);
	}
	
	
	
	/*
	 * Methods
	 */
	public void setUp(String idMQTT, String askTaskMessage) {
		this.idMQTT = idMQTT;
		this.askTaskMessage = askTaskMessage;
		this.callBeforeTimeout = 3;
	}
	
	/**
	 * If you didn't specify parameters in the Ctor or used the overloaded update method,
	 * this will boot with default parameters. You should read about those to get a heads up.
	 */
	public void boot() {
		
		//this.subscriber = new Subscriber(this.idMQTT);
		this.subscriber = Subscriber.getSubscriber();
		this.subscriber.setUp(this.idMQTT);
		try {
			this.subscriber.subToFeedbackTopicList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] menu = null;
		App.getTaskMenuLock().setUpdating(true);
		
		while(App.getTaskMenuLock().isUpdating() && this.callBeforeTimeout > 0) {
			
			try {
				System.out.println("mvp client info : Asking for messages. Remaining calls : " + this.callBeforeTimeout);
				this.subscriber.askForTaskList(this.askTaskMessage);
				menu = App.getTaskMenuLock().getTaskPaths(10);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			this.callBeforeTimeout--;
		}
		
		if(menu == null)
		{
			/*
			 * TODO if time allows it. 
			 * A bit barbarish. 
			 * Maybe a full on showOptionDialog
			 * with the possibilities to actually boot the app again w.o. closing it.
			 */
			JOptionPane.showMessageDialog(null, 
					"Failed to load task list."
					+ "\nPlease, try and reboot this app."
					+ "\nIf the problem persist, contact your administrator.");
			System.exit(0);
		}
		else
		{
			FormTask formTask = new FormTask(menu);			
		}
		
		
	}
}
