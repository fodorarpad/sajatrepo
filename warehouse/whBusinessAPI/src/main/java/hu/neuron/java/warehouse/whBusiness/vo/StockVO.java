package hu.neuron.java.warehouse.whBusiness.vo;

import java.io.Serializable;

public class StockVO implements Serializable {

	private static final long serialVersionUID = 5805718801339863878L;
	
	private Long id;


	private WarehouseVO warehouse;
	

	private WareVo ware;
	
	
	private int piece;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	


	public WarehouseVO getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseVO warehouse) {
		this.warehouse = warehouse;
	}

	public WareVo getWare() {
		return ware;
	}

	public void setWare(WareVo ware) {
		this.ware = ware;
	}

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}
	
	
	



}
