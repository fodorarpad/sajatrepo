package hu.neuron.java.warehouse.whWeb.webVO;

import java.io.Serializable;

public class WareWebVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String wareName;
	
	private int itemNumber;

	public String getWareName() {
		return wareName;
	}

	public void setWareName(String wareName) {
		this.wareName = wareName;
	}

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	
}
