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

	private String askTopicList = "/ask/topiclist";
	private Dictionary<String, Boolean> subscribedTopic = new Hashtable();
	private String topicFeedback = "/feedback/topiclist";
	

	private void setUp() {
		this.subscribedTopic.put(this.topicFeedback, false);
	}
	
	public Subscriber() 
	{
		this.setUp();
	}
	
	public void subToFeedbackTopicList() throws Exception{
		if((this.subscribedTopic.get(this.topicFeedback))) 
		{
			throw new Exception("Already subscribed to topic : " + this.topicFeedback);
		}
		MqttAsyncClient client;
		try {
			client = new MqttAsyncClient("tcp://127.0.0.1", "Subscriber");
			client.setCallback(new TopicListCallBack());
			
			IMqttToken token = client.connect();
			token.waitForCompletion();
			
			client.subscribe(this.topicFeedback, 0);
			this.subscribedTopic.put(this.topicFeedback, true);
			this.askForTopicList("all");
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void askForTopicList(String message) {

        
        int qos             = 2;
        String broker       = "tcp://localhost:1883";
        String clientId     = "6";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            sampleClient.connect(connOpts);
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(qos);
            sampleClient.publish(askTopicList, mqttMessage);
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
	
}
