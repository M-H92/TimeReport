package mvp.mqtt.receiver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import mvp.dal.model.Task;
import mvp.dal.provider.TaskProvider;

public class Subscriber {

	private List<String> topicList = new ArrayList<String>();
	
	public void subToActiveTasks(List<Task> tasks) 
	{
		
		List<String> topics = new ArrayList<String>();
		
		for(Task task : tasks) 
		{
			if(task.getParentTask() == null) 
			{
				List<String> topicsPartialList = new ArrayList<String>();
				topicsPartialList.add("");
				task.getActiveTopics(topicsPartialList);
				topics.addAll(topicsPartialList);
			}
		}
		
		for(String string : topics) 
		{
			System.out.println(string);
		}
	}
	
	public void subToTasks(List<Task> tasks) 
	{
		try {
			MqttAsyncClient client = new MqttAsyncClient("tcp://127.0.0.1", "task_Subscriber");
			client.setCallback(new ProjectCallBack());
			
			IMqttToken token = client.connect();
			token.waitForCompletion();

			
			List<String> topics = new ArrayList<String>();
			
			for(Task task : tasks) 
			{
				if(task.getParentTask() == null) 
				{
					List<String> topicsPartialList = new ArrayList<String>();
					task.getTopics(topicsPartialList);
					topics.addAll(topicsPartialList);
				}
			}
			
			
			for(String string : topics) 
			{
				this.topicList.add(string);
				client.subscribe(string, 0);
			}
		}
		catch (MqttException e){
			e.printStackTrace();
		}
	}
	
	 /*Tested + TODO.
	 * Does connect to all topics
	 * TODO
	 * Manage error message
	 * */
	public void subAll() {
		
		try {
			MqttAsyncClient client = new MqttAsyncClient("tcp://127.0.0.1", "task_Subscriber");
			client.setCallback(new ProjectCallBack());
			
			IMqttToken token = client.connect();
			token.waitForCompletion();
			
			
			} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void subToAskTopicList() throws Exception {
		/*
		if(this.topicList.size() == 0) 
		{
			throw new Exception("You must subscribe to tasks topic before subscribing to askTopicList");
		}
		*/
		MqttAsyncClient client;
		try {
			client = new MqttAsyncClient("tcp://127.0.0.1", "askTopic_Subscriber");
			client.setCallback(new TopicListCallBack(this.getProjectList()));
			
			IMqttToken token = client.connect();
			token.waitForCompletion();
			
			client.subscribe("/ask/topiclist", 0);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void subToTimesheet() {
		MqttAsyncClient client;
		try {
			client = new MqttAsyncClient("tcp://127.0.0.1", "timesheet_Subscriber");
			client.setCallback(new TimesheetCallBack());
			
			IMqttToken token = client.connect();
			token.waitForCompletion();
			
			client.subscribe("/timesheet", 0);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<String> getProjectList() {
		List<String> formatedList = new ArrayList();
		
		List<Task> projects = new TaskProvider().getProjects();
		for(Task project : projects) {
			List<String> taskPath = new ArrayList<String>(); 
			project.getTopics(taskPath);
			for(String task : taskPath) {
				formatedList.add(task);
			}
		}
		return formatedList;
	}
}
