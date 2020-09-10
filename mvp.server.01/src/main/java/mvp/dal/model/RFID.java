package mvp.dal.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity(name="rfid")
@Table(name="rfid")
public class RFID {


/* ----------------------------------------------
 * Fields
 ----------------------------------------------*/
	@Id
	private String rfid;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "idEmployee")
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
