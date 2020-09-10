package mvp.mqtt.sender;

import javax.swing.JOptionPane;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.fasterxml.jackson.databind.JsonNode;

import mvp.Json.Json;
import mvp.bl.model.Timesheet;
import mvp.dto.FeedbackTimesheet;
import mvp.gui.FormTask;

public class TimesheetFeedbackCallBack extends CallBack {

	private Timesheet timesheet;
	

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		
		JsonNode node = Json.parse(message.toString());
		
        FeedbackTimesheet fbo  = Json.fromJson(node, FeedbackTimesheet.class);

        if(this.timesheet.getStartDate().toString().equals(fbo.startDate)
        		&& this.timesheet.getEndDate().toString().equals(fbo.endDate)
        		&& this.timesheet.getEmployee().getId() == fbo.idEmployee
        		&& this.timesheet.getTask().getId() == fbo.idTask) 
        {
        	String feedbackInformation;
        	if(fbo.isSaved) {
        		feedbackInformation = "timesheet saved in database";
        	}
        	else {
        		feedbackInformation = "timesheet couldn't be saved in database";
        	}

			JOptionPane.showMessageDialog(null, feedbackInformation);
        }

		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

	public TimesheetFeedbackCallBack(Timesheet timesheet) {
		this.timesheet = timesheet;
	}

}
