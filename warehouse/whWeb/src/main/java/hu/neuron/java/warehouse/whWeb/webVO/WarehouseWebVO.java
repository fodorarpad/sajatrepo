package hu.neuron.java.warehouse.whWeb.webVO;

import java.io.Serializable;

public class WarehouseWebVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String warehouseId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	

}
