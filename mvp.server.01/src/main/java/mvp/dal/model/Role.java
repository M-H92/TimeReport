package mvp.dal.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="role")
@Table(name="role")
public class Role {
	
/* ----------------------------------------------
 * Fields
 ----------------------------------------------*/
	@Id
	@GeneratedValue
	private int id;
	private String name;


/* ----------------------------------------------
 * Link fields
 ----------------------------------------------*/
	@OneToMany(mappedBy = "role", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
	private Set<EmployeeRoleTaskLink> ERTLink;
	
	

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
		
		return "Role is " + this.name;
	}
	
	
	
	
	
	
}
