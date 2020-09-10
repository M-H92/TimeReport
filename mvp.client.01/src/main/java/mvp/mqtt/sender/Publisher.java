package mvp.mqtt.sender;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.fasterxml.jackson.databind.JsonNode;

import mvp.Json.Json;
import mvp.bl.model.Timesheet;

public class Publisher {
	//TODO take out FormTask from those argument. Drop the feature if you don't know how to do it properly
	public void publishTimesheet(Timesheet timesheet) {
			
		String topic = "timesheet";
		
	    JsonNode json = Json.toJson(timesheet);
	    String content = Json.stringify(json);
	    
	    
	    int qos             = 2;
	    String broker       = "tcp://localhost:1883";
	    String clientId     = "10";
	    MemoryPersistence persistence = new MemoryPersistence();
	
	    try {
	        MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
	        MqttConnectOptions connOpts = new MqttConnectOptions();
	        connOpts.setCleanSession(true);
	        sampleClient.connect(connOpts);
	        MqttMessage feedbackMessage = new MqttMessage(content.getBytes());
	        feedbackMessage.setQos(qos);
	        sampleClient.publish(topic, feedbackMessage);
	        sampleClient.disconnect();
	    } catch(MqttException me) {
	        System.out.println("reason "+me.getReasonCode());
	        System.out.println("msg "+me.getMessage());
	        System.out.println("loc "+me.getLocalizedMessage());
	        System.out.println("cause "+me.getCause());
	        System.out.println("excep "+me);
	        me.printStackTrace();
	    }
    
	    Subscriber.getSubscriber().subToTimesheetFeedback(new TimesheetFeedbackCallBack(timesheet));

	}
	
}
