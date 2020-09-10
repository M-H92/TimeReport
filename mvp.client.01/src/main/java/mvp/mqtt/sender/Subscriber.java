package mvp.mqtt.sender;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class Subscriber {
	/*
	 * Static fields
	 */
	private static Subscriber subscriber;
	
	
	
	/*
	 * Fields
	 */
	private String askTopicList;
	private Dictionary<String, Boolean> subscribedTopic;
	private String topicFeedback;
	private String idPrefixe;
	private boolean isSetup;
	
	
	
	/*
	 * Static methods
	 */
	public static Subscriber getSubscriber() 
	{
		if(Subscriber.subscriber == null) {
			Subscriber.subscriber = new Subscriber();
			Subscriber.subscriber.isSetup = false;
		}
		
		return Subscriber.subscriber;
	}
	
	/*
	 * Methods
	 */
	public void setUp(String idPrefixe) {
		this.idPrefixe = idPrefixe;
		this.askTopicList = "task/ask";
		this.topicFeedback = "task/feedback";
		
		this.subscribedTopic = new Hashtable();
		
		this.subscribedTopic.put(this.topicFeedback, false);
		
		this.isSetup = true;
	}
	
	public void subToFeedbackTopicList() throws Exception{
		this.setupCheck();
		//TODO
		if(isSubscribed(this.topicFeedback)) 
		{
			//throw new Exception("Already subscribed to topic : " + this.topicFeedback);
			System.out.println("mvp client info : Tried to sub to topicFeedback while already subbed");
			return;
		}
		MqttAsyncClient client;
		try {
			client = new MqttAsyncClient("tcp://127.0.0.1", this.idPrefixe + "_topicFeedback");
			client.setCallback(new TopicListCallBack());
			
			IMqttToken token = client.connect();
			token.waitForCompletion();
			
			client.subscribe(this.topicFeedback, 2);
			this.subscribedTopic.put(this.topicFeedback, true);
			
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void subToTimesheetFeedback(CallBack callback) {
	    MqttAsyncClient client;
		try {
			client = new MqttAsyncClient("tcp://127.0.0.1", this.idPrefixe + "timesheet_feedback");
			client.setCallback(callback);
			
			IMqttToken token = client.connect();
			token.waitForCompletion();
			
			client.subscribe("timesheet/feedback", 2);
			
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void askForTaskList(String message) throws Exception {
		this.setupCheck();
		
		//Calling this method w.o. being subscribed to the feedback topic may be intended but is unlikely.
		//So I send a message in the console rather than throwing an exception.
		if(!isSubscribed(this.topicFeedback)) {
			System.out.println("mvp client info : Asked for task list without subscribing to the feedback topic beforehand.");
		}
        
        int qos             = 2;
        String broker       = "tcp://localhost:1883";
        String clientId     = this.idPrefixe + "_askTaskList";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            sampleClient.connect(connOpts);
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(qos);
            sampleClient.publish(this.askTopicList, mqttMessage);
            sampleClient.disconnect();
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
		
	}
	
	private void setupCheck() throws Exception {
		if(!this.isSetup)
			throw new Exception("mvp error : your mqtt subscriber was not set up correctly");
	}

	private boolean isSubscribed(String topic) {
		if(this.subscribedTopic.get(topic))
			return true;
		
		return false;
	}
	
	
	
	/*
	 * Ctor 
	 */
	private Subscriber() 
	{
		
	}
	
	
}
