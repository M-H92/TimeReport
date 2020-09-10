package mvp.mqtt.receiver;

import java.util.List;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.fasterxml.jackson.databind.JsonNode;

import mvp.Json.Json;

public class TopicListCallBack extends CallBack {
	
	private List<String> topicList;

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {

		System.out.println("message received : " + message);
		String feedbackTopic        = "/feedback/topiclist";
		
		//Stringify JSON !
        JsonNode json = Json.toJson(this.topicList);
        String content = Json.stringify(json);
        
        
        int qos             = 2;
        String broker       = "tcp://localhost:1883";
        String clientId     = "5";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            sampleClient.connect(connOpts);
            MqttMessage feedbackMessage = new MqttMessage(content.getBytes());
            feedbackMessage.setQos(qos);
            sampleClient.publish(feedbackTopic, feedbackMessage);
            sampleClient.disconnect();
            //System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

	
	public TopicListCallBack(List<String> topicList) {
		this.topicList = topicList;
	}
}
