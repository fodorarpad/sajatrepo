package hu.neuron.java.warehouse.whCore.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Transport_Details")
public class TransportDetails extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long transportId;

	//id, csak odagenerálja mögé a _id -t
	@ManyToOne(targetEntity = Ware.class)
	private Ware ware;

	private int piece;

	public Ware getWare() {
		return ware;
	}

	public void setWare(Ware ware) {
		this.ware = ware;
	}

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}

	public Long getTransportId() {
		return transportId;
	}

	public void setTransportId(Long transportId) {
		this.transportId = transportId;
	}

	@Override
	public String toString() {
		return "TransportDetails [transportId=" + transportId + ", ware="
				+ ware + ", piece=" + piece + "]";
	}	

}
