package hu.neuron.java.warehouse.whCore.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "stockhistory")
public class StockHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(targetEntity = Warehouse.class)
	private Warehouse warehouse;

	@ManyToOne(targetEntity = Ware.class)
	private Ware ware;

	private int new_piece;

	private int old_piece;

	@Column(name = "changeTime", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
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

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Ware getWare() {
		return ware;
	}

	public void setWare(Ware ware) {
		this.ware = ware;
	}


}
