package hu.neuron.java.warehouse.whBusiness.vo;

import java.io.Serializable;
import java.util.Date;

public class StockHistoryVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private WarehouseVO warehouse;

	private WareVo ware;

	private int new_piece;

	private int old_piece;

	private Date changeTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNew_piece() {
		return new_piece;
	}

	public void setNew_piece(int new_piece) {
		this.new_piece = new_piece;
	}

	public int getOld_piece() {
		return old_piece;
	}

	public void setOld_piece(int old_piece) {
		this.old_piece = old_piece;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
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

}
