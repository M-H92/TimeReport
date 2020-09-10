package mvp.bl.model;


/**
 * business layer of the RFID class
 */
public class RFID {


/* ----------------------------------------------
 * Fields
 ----------------------------------------------*/
	private String rfid;

	private Employee employee;


/* ----------------------------------------------
 * Get / Set
 ----------------------------------------------*/
	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


/* ----------------------------------------------
 * Methods
 ----------------------------------------------*/
	
	@Override
	public String toString() {
		return "RFID [rfid=" + rfid + ", \remployee=" + employee.getFirstName() + " " + employee.getLastName() + "]";
	}
	
	
}
