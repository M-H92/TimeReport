package mvp.bl.model;

import java.util.Set;


public class Role {
	
/* ----------------------------------------------
 * Fields
 ----------------------------------------------*/
	private int id;
	private String name;


/* ----------------------------------------------
 * Link fields
 ----------------------------------------------*/
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
