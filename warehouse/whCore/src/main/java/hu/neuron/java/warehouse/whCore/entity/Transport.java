package hu.neuron.java.warehouse.whCore.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Transport")
public class Transport extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * A feladó raktár id-ja
	 */
	//id, csak odagenerálja mögé a _id -t
	@ManyToOne(targetEntity = Warehouse.class)
	private Long fromWarehouse;

	/**
	 * Az átvevő raktár id-ja
	 */
	//id, csak odagenerálja mögé a _id -t
	@ManyToOne(targetEntity = Warehouse.class)
	private Long toWarehouse;
	
	/**
	 * A szállítás státusza
	 */
	private String transportStatus;

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

	@Override
	public String toString() {
		return "Transport [fromWarehouse=" + fromWarehouse + ", toWarehouse="
				+ toWarehouse + ", transportStatus=" + transportStatus + "]";
	}
	
}
