package mvp.server;

import mvp.mqtt.receiver.Subscriber;

public class App {

	/*
	 * Fields
	 */
	private String idMQTT ="";

	
	/*
	 * Get/Set
	 */
	public String getIdMQTT() {
		return idMQTT;
	}
	
	
	
	/*
	 * CTor
	 */
	//Default setup. Multiple mvp server app can't run on this setting w.o. creating conflicts
	public App() {
		this.setUp();
	}
	

	public App(String idMQTT) {
		this.setUp(idMQTT);
	}

	
	
	/*
	 * Methods
	 */
	//Default setup. Multiple mvp server app can't run on this setting w.o. creating conflicts
	public void setUp() {
		this.idMQTT = "serv_01";
	}

	public void setUp(String idMQTT) {
		this.idMQTT = idMQTT;
	}
	
	public void boot() {
		Subscriber subscriber = new Subscriber(this.idMQTT);
		subscriber.subToTimesheet();
		try {
			subscriber.subToTaskAskTopic();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
