package hu.neuron.java.warehouse.whCore.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = Warehouse.class)
	private Warehouse warehouse;

	@ManyToOne(targetEntity = Ware.class)
	private Ware ware;

	private int piece;

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

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}

}
