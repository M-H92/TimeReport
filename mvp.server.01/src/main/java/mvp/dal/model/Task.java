package mvp.dal.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="task")
@Table(name="task")
public class Task {


/* ----------------------------------------------
 * Fields
 ----------------------------------------------*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String highlight;
	

/* ----------------------------------------------
 * Link fields
 ----------------------------------------------*/
	@OneToMany(mappedBy = "task", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
	private Set<EmployeeRoleTaskLink> ERTLink;
	
	@Column(name="idStatus")
	private TaskStatus status;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="idParentTask")
	private Task parentTask;
	
	@OneToMany(mappedBy="parentTask", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private List<Task> subtasks;
	
	@OneToMany(mappedBy = "task", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
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
		
		//test code
		String[] currentNames = current.split("/");
		current = String.valueOf(this.getId());
		for(int i = 1; i < currentNames.length ; i++) {
			current += "/" + currentNames[i];
		}
		
		//end test code
		
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
		if(this.status == TaskStatus.Doing)
		{
			
			current += "/" + this.getName();
		
		//test code
		String[] currentNames = current.split("/");
		current = String.valueOf(this.getId());
		for(int i = 1; i < currentNames.length ; i++) {
			current += "/" + currentNames[i];
		}
		
		//end test code
		
		topicPath.set(topicPath.size()-1, current);
		}

		for(Task subtask : this.subtasks) 
		{
			if(subtask.status == TaskStatus.Doing) {				
				topicPath.add(current);				
				subtask.getActiveTopics(topicPath);
			}
		}
		
		return;
		/*
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
		*/
	}
	public String getTopic() {
		return this.id + "/" + this.name;
	}
	
	
	
}
