package hu.neuron.java.warehouse.whBusiness.vo;

import java.io.Serializable;

public class TransportVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	/**
	 * A feladó raktár id-ja
	 */
	private Long fromWarehouse;

	/**
	 * Az átvevő raktár id-ja
	 */
	private Long toWarehouse;
	
	/**
	 * A szállítás státusza
	 */
	private String transportStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFromWarehouse() {
		return fromWarehouse;
	}

	public void setFromWarehouse(Long fromWarehouse) {
		this.fromWarehouse = fromWarehouse;
	}

	public Long getToWarehouse() {
		return toWarehouse;
	}

	public void setToWarehouse(Long toWarehouse) {
		this.toWarehouse = toWarehouse;
	}

	public String getTransportStatus() {
		return transportStatus;
	}

	public void setTransportStatus(String transportStatus) {
		this.transportStatus = transportStatus;
	}

}
