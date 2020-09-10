package mvp.mqtt.receiver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import mvp.dal.model.Task;
import mvp.dal.provider.TaskProvider;

public class Subscriber {

	/*
	 * Fields
	 */
	private List<String> topicList = new ArrayList<String>();
	private String idPrefixe;
	
	
	
	/*
	 * Methods
	 */
	public void subToTaskAskTopic() throws Exception {

		MqttAsyncClient client;
		try {
			client = new MqttAsyncClient("tcp://127.0.0.1", this.idPrefixe + "_task_ask");
			client.setCallback(new TopicListCallBack());
			
			IMqttToken token = client.connect();
			token.waitForCompletion();
			
			client.subscribe("task/ask", 0);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void subToTimesheet() {
		MqttAsyncClient client;
		try {
			client = new MqttAsyncClient("tcp://127.0.0.1", this.idPrefixe + "_timesheet");
			client.setCallback(new TimesheetCallBack());
			
			IMqttToken token = client.connect();
			token.waitForCompletion();
			
			client.subscribe("timesheet", 0);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Ctor
	 */
	public Subscriber(String idPrefixe) {
		this.idPrefixe = idPrefixe;
	}
}
