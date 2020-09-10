package mvp.mqtt.sender;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.databind.JsonNode;

import mvp.Json.Json;
import mvp.thread.lock.TaskMenuLock;
//import mvp.client.App;

public class TopicListCallBack extends CallBack {

	/*
	 * Event methods
	 */
	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		
		System.out.println("message received : " + message + " from thread " + Thread.currentThread().getName());
		
		
		
		
		//Json json = new Json();
		JsonNode node = Json.parse(message.toString());
		String[] values = Json.fromJson(node, String[].class);


		TaskMenuLock.getTaskMenuLock().setTaskPaths(values);
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

}
