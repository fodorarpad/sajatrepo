package hu.neuron.java.warehouse.whCore.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Manager")
public class Manager extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String userName;

	private String warehouseId;

	public String getWarehouse() {
		return warehouseId;
	}

	public void setWarehouse(String warehouse) {
		this.warehouseId = warehouse;
	}

	public String getUser() {
		return userName;
	}

	public void setUser(String user) {
		this.userName = user;
	}
	
	
	
	
	
	
	
}
