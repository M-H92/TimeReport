package mvp.bl.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;


public class Task {


/* ----------------------------------------------
 * Fields
 ----------------------------------------------*/
	private int id;
	private String name;
	private String description;
	private String highlight;
	

/* ----------------------------------------------
 * Link fields
 ----------------------------------------------*/
	private Set<EmployeeRoleTaskLink> ERTLink;
	
	private TaskStatus status;
	
	private Task parentTask;
	
	private List<Task> subtasks;
	
	private Set<Timesheet> timesheets;
	

/* ----------------------------------------------
 * Get/Set
 ----------------------------------------------*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHighlight() {
		return highlight;
	}
	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}
	public TaskStatus getStatus() {
		return status;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	public List<Task> getSubtasks() {
		return subtasks;
	}
	public void setSubtasks(List<Task> subtasks) {
		this.subtasks = subtasks;
	}
	
	public Task getParentTask() {
		return parentTask;
	}
	public void setParentTask(Task parentTask) {
		this.parentTask = parentTask;
	}
	public Set<EmployeeRoleTaskLink> getERTLink() {
		return ERTLink;
	}
	public void setERTLink(Set<EmployeeRoleTaskLink> eRTLink) {
		ERTLink = eRTLink;
	}
	
	public Set<Timesheet> getTimesheets() {
		return timesheets;
	}
	public void setTimesheets(Set<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}
	
	/* ----------------------------------------------
 * Method
 ----------------------------------------------*/
	@Override
	public String toString() {
		return "Task [\r id = " + id + "\r name = " + name + "\r description = " + description + "\r highlight = " + highlight
				+ "\r status = " + status +"]" + "\r----------------\r----------------\r parent task = " + parentTask ;
	}
	

	public void getTopics(List<String> topicPath) 
	{
		if(topicPath == null || topicPath.size() == 0) {
			topicPath.add("");
		}
		String current = topicPath.get(topicPath.size()-1);
		current += "/" + this.getName();
		
		topicPath.set(topicPath.size()-1, current);

		for(Task subtask : this.subtasks) 
		{
			topicPath.add(current);
			subtask.getTopics(topicPath);
		}
		
		return;
	}
	
	public void getActiveTopics(List<String> topicPath) 
	{
		if(topicPath == null || topicPath.size() == 0) {
			topicPath.add("");
		}
		String current = topicPath.get(topicPath.size()-1);
		current += "/" + this.getName();
		
		topicPath.set(topicPath.size()-1, current);

		for(Task subtask : this.subtasks) 
		{
			if(subtask.getStatus() == TaskStatus.Doing) {
				topicPath.add(current);
				subtask.getActiveTopics(topicPath);
			}
		}
		
		return;
	}
	

/* ----------------------------------------------
 * Ctor
 ----------------------------------------------*/
	
	public Task() {}
	
	public Task(int id) {
		this.id = id;
	}
	
	
	
}
