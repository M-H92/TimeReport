package mvp.mqtt.receiver;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public abstract class CallBack implements MqttCallback {

		abstract public void connectionLost(Throwable cause);

		abstract public void messageArrived(String topic, MqttMessage message) throws Exception;

		abstract public void deliveryComplete(IMqttDeliveryToken token);
}
