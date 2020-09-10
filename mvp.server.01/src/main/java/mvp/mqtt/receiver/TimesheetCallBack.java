package mvp.mqtt.receiver;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.hibernate.annotations.Parent;

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
		

		JsonNode node = Json.parse(message.toString());
		Timesheet timesheet = Json.fromJson(node, Timesheet.class);
		
		boolean isSaved = new TimesheetProvider().createTimesheet(timesheet) == 0 ? true : false;
        if(isSaved) {
        	System.out.println("mvp server info : timesheet send to db : «\n" + message);        	
        }
        else {
        	System.out.println("mvp server info : couldn't save the timesheet : «\n" + message);  
        }
        
        
        int qos             = 2;
        String broker       = "tcp://localhost:1883";
        String clientId     = "server_timesheet_feedback";
        MemoryPersistence persistence = new MemoryPersistence();
        
        /*TODO clean this mess, ffs.
         * This is not C#, 
         * there's no anonymous type 
         * and that bit of code will be awful to maintain*/
        class FeedbackObject {
        	public String startDate;
        	public String endDate;
        	public int idEmployee;
        	public int idTask;
        	public boolean isSaved;
        }
        FeedbackObject fbo = new FeedbackObject();
        fbo.startDate = timesheet.getStartDate().toString();
        fbo.endDate = timesheet.getEndDate().toString();
        fbo.idEmployee = timesheet.getEmployee().getId();
        fbo.idTask = timesheet.getTask().getId();
        fbo.isSaved = isSaved;
        
        node = Json.toJson(fbo);
        String feedBackMessage = Json.stringify(node);
        
        

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            sampleClient.connect(connOpts);
            message = new MqttMessage(feedBackMessage.getBytes());
            message.setQos(qos);
            sampleClient.publish("timesheet/feedback", message);
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

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

}
