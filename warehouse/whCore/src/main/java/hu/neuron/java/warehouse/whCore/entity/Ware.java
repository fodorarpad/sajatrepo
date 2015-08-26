package hu.neuron.java.warehouse.whCore.entity;

import hu.neuron.java.warehouse.whCore.entity.BaseEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Ware")
//@NamedQuery(name = "Ware.findWareByName", query = "SELECT u FROM Ware u  WHERE u.Name = :Name")
public class Ware extends BaseEntity  {
	

	private static final long serialVersionUID = 1L;
	@Column(unique = true, nullable = false)
	private String wareName;
	@Column(unique = true, nullable = false)
	private int itemNumber;
	private String description;
	private int visible;
	
	public String getWareName() {
		return wareName;
	}
	public void setWareName(String name) {
		wareName = name;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Ware [Name=" + wareName + ", ItemNumber=" + itemNumber
				+ ", Description=" + description + "]";
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
}
