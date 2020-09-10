package mvp.mqtt.receiver;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.databind.JsonNode;

import mvp.Json.Json;
import mvp.dal.model.Timesheet;
import mvp.dal.provider.TimesheetProvider;

public class TimesheetCallBack extends CallBack {

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		

		Json json = new Json();
		JsonNode node = json.parse(message.toString());
		Timesheet timesheet = json.fromJson(node, Timesheet.class);
		
		new TimesheetProvider().createTimesheet(timesheet);
        
		System.out.println("timesheet send to db : «\n" + message);
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

}
