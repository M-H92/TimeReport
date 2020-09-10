package mvp.bl.model;


/**
 * Business layer of the EmployeeRoleTaskLink class
 */
public class EmployeeRoleTaskLink {

	/*
	 * Fields
	 */
	private int id;
	
	private Employee employee;

	private Role role;

	private Task task;

	
	
	/*
	 * get/set
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	
	
	/*
	 * methods 
	 */
	@Override
	public String toString() {
		return "ERT of id " + this.id;
	}

	

	
	
	
	
	
}
