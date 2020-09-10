package mvp.bl.model;

import java.util.Date;


public class Timesheet {


/* ----------------------------------------------
 * Fields
 ----------------------------------------------*/
	private int id;
	private Date startDate;
	private Date endDate;
	

/* ----------------------------------------------
 * Link fields
 ----------------------------------------------*/
	private Employee employee;
	
	private Task task;

	

/* ----------------------------------------------
 * Get/Set
 ----------------------------------------------*/


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

/* ----------------------------------------------
 * Ctor
 ----------------------------------------------*/
	
	public Timesheet() {}
	
	public Timesheet(int idEmployee, int idTask, Date startDate, Date endDate) {
		
		this.setEmployee(new Employee(idEmployee));
		this.setTask(new Task(idTask));
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}

}
