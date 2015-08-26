package hu.neuron.java.warehouse.whWeb.controller;

import hu.neuron.java.warehouse.whBusiness.service.WareServiceLocal;
import hu.neuron.java.warehouse.whBusiness.vo.WareVo;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(name = "wareController")
public class WareController implements Serializable {

	private static final long serialVersionUID = 2618227417754884855L;

	private LazyWareModel lazyWareModel;

	private WareVo selectedWare;

	private String newWareName;
	private int newItemNumber;
	private String newDescription;
	private String updateWareName;
	private int updateItemNumber;
	private String updateDescription;

	@EJB(beanName = "WareService")
	WareServiceLocal wareService;

	@PostConstruct
	public void init() {

		setLazyWareModel(new LazyWareModel(getWareService()));
	}

	public void saveNewWare() {
		WareVo wareVo = new WareVo();
		wareVo.setWareName(newWareName);
		wareVo.setDescription(newDescription);
		wareVo.setItemNumber(newItemNumber);
		wareVo.setVisible(1);

		getWareService().saveWare(wareVo);
	}

	public void deleteWare() {

		selectedWare.setVisible(0);
		
		getWareService().saveWare(selectedWare);

	}

	WareVo test() {

		return selectedWare;
	}

	public void onRowSelect(SelectEvent event) {

		selectedWare = (WareVo) event.getObject();
		updateWareName = selectedWare.getWareName();
		updateItemNumber = selectedWare.getItemNumber();
		updateDescription = selectedWare.getDescription();

	}

	public void updateWare() {

		selectedWare.setWareName(updateWareName);
		selectedWare.setItemNumber(updateItemNumber);
		selectedWare.setDescription(updateDescription);
		// System.out.println("II**II :" + selectedWare);
		getWareService().saveWare(selectedWare);

	}

	public String getUpdateWareName() {
		return updateWareName;
	}

	public void setUpdateWareName(String updateWareName) {
		this.updateWareName = updateWareName;
	}

	public int getUpdateItemNumber() {
		return updateItemNumber;
	}

	public void setUpdateItemNumber(int updateItemNumber) {
		this.updateItemNumber = updateItemNumber;
	}

	public String getUpdateDescription() {
		return updateDescription;
	}

	public void setUpdateDescription(String updateDescription) {
		this.updateDescription = updateDescription;
	}

	public WareVo getSelectedWare() {
		return selectedWare;
	}

	public void setSelectedWare(WareVo selectedWare) {
		this.selectedWare = selectedWare;
	}

	public String getNewWareName() {
		return newWareName;
	}

	public void setNewWareName(String newWareName) {
		this.newWareName = newWareName;
	}

	public int getNewItemNumber() {
		return newItemNumber;
	}

	public void setNewItemNumber(int newItemNumber) {
		this.newItemNumber = newItemNumber;
	}

	public String getNewDescription() {
		return newDescription;
	}

	public void setNewDescription(String newDescription) {
		this.newDescription = newDescription;
	}

	public WareServiceLocal getWareService() {
		return wareService;
	}

	public void setWareService(WareServiceLocal wareService) {
		this.wareService = wareService;
	}

	public LazyWareModel getLazyWareModel() {
		return lazyWareModel;
	}

	public void setLazyWareModel(LazyWareModel lazyWareModel) {
		this.lazyWareModel = lazyWareModel;
	}
}
