package hu.neuron.java.warehouse.whBusiness.vo;

import java.io.Serializable;

public class ManagerVO implements Serializable {

	private static final long serialVersionUID = -898561653587590872L;
	
	private Long id;
	
	private String warehouse;

	private String user;

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
