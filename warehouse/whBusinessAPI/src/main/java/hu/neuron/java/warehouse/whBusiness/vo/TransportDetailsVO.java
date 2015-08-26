package hu.neuron.java.warehouse.whBusiness.vo;

import java.io.Serializable;

public class TransportDetailsVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Long transportId;

	private Long ware;

	private int piece;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTransportId() {
		return transportId;
	}

	public void setTransportId(Long transportId) {
		this.transportId = transportId;
	}

	public Long getWare() {
		return ware;
	}

	public void setWare(Long ware) {
		this.ware = ware;
	}

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}
	
}
