package mvp.bl.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * Business layer of the Employee class.
 */
public class Employee {

/* ----------------------------------------------
 * Fields
 ----------------------------------------------*/
	private int id;
	private String firstName;
	private String lastName;
	private String password;


/* ----------------------------------------------
 * Linking fields
 ----------------------------------------------*/
	private Set<EmployeeRoleTaskLink> ERTLink;
	
	private Set<Timesheet> timesheets;

	private Set<RFID> rfids;
	
	

/* ----------------------------------------------
 * Get/Set
 ----------------------------------------------*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Timesheet> getTimesheets() {
		return timesheets;
	}

	public void setTimesheets(Set<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}

	public Set<RFID> getRfids() {
		return rfids;
	}

	public void setRfids(Set<RFID> rfids) {
		this.rfids = rfids;
	}

	public Set<EmployeeRoleTaskLink> getERTLink() {
		return ERTLink;
	}

	public void setERTLink(Set<EmployeeRoleTaskLink> eRTLink) {
		ERTLink = eRTLink;
	}


/* ----------------------------------------------
 * Methods
 ----------------------------------------------*/
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "I'm " + this.firstName + " " + this.lastName + ", an employee.";
	}

/* ----------------------------------------------
 * Ctor
 ----------------------------------------------*/
	public Employee() {}
	
	public Employee(int id) {
		this.id = id;
	}
	
}
