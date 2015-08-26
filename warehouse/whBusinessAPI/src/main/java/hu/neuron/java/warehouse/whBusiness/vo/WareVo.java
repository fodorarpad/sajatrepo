package hu.neuron.java.warehouse.whBusiness.vo;

import java.io.Serializable;

public class WareVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long Id;
	private String wareName;
	public String getWareName() {
		return wareName;
	}
	public void setWareName(String wareName) {
		this.wareName = wareName;
	}
	private int itemNumber;
	private String description;
	private int visible;

	
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
	public Long getId() {
		// TODO Auto-generated method stub
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	@Override
	public String toString() {
		return "WareVo [Id=" + Id + ", wareName=" + wareName + ", ItemNumber="
				+ itemNumber + ", Description=" + description + "]";
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	
	
}
