package hu.neuron.java.warehouse.whWeb.controller;

import hu.neuron.java.warehouse.whBusiness.service.WareOrderLOcal;
import hu.neuron.java.warehouse.whBusiness.service.WareServiceLocal;
import hu.neuron.java.warehouse.whBusiness.service.WarehouseServiceLocal;
import hu.neuron.java.warehouse.whBusiness.vo.StockVO;
import hu.neuron.java.warehouse.whBusiness.vo.WareVo;
import hu.neuron.java.warehouse.whBusiness.vo.WarehouseVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ViewScoped
@ManagedBean(name = "wareOrderConttroller")
public class WareOrderConttroller implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB(name = "WareOrderService")
	private WareOrderLOcal wareOrder;

	@EJB(name = "WarehouseService")
	private WarehouseServiceLocal warehouseService;

	@EJB(name = "WareService")
	private WareServiceLocal wareService;

	private int db;

	private LinkedList<Integer> pieces;

	private Collection<WarehouseVO> warehouses;

	private Collection<String> whNames;

	private String selectedWhNames;

	private List<WareVo> wares;

	private Collection<WareVo> selectedware;

	private Collection<String> wareNames;

	private Collection<String> selectedwareNames;

	@PostConstruct
	void init() {
		pieces = new LinkedList<Integer>();
		whNames = new ArrayList<String>();
		warehouses = new ArrayList<WarehouseVO>();
		warehouses = warehouseService.findAll();
		for (WarehouseVO warehouseVO : warehouses) {
			whNames.add(warehouseVO.getName());
		}

		wareNames = new ArrayList<String>();
		wares = new ArrayList<WareVo>();
		wares = wareService.getWares();
		for (WareVo wareVo : wares) {
			wareNames.add(wareVo.getWareName());
		}
	}

	public void order() {
		WarehouseVO wh;
		WareVo ware;
		StockVO order = new StockVO();
		try {

			wh = warehouseService.findWarehouseWarehouseId("Default Warehouse");

			order.setWarehouse(wh);
			for (String wareName : selectedwareNames) {
				ware = wareService.findWareByName(wareName);
				order.setWare(ware);
				order.setPiece(pieces.getLast());
				wareOrder.order(order);
				pieces.removeLast();
			}
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes ",
							"Order"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error ",
							"Order"));
			e.printStackTrace();
		} finally {
			pieces.clear();
		}

	}

	public Collection<WareVo> getSelectedware() {
		return selectedware;
	}

	public void setSelectedware(Collection<WareVo> selectedware) {
		this.selectedware = selectedware;
	}

	public void addMessage(String summary) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Siker", summary));
	}

	public Collection<WarehouseVO> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(Collection<WarehouseVO> warehouses) {
		this.warehouses = warehouses;
	}

	public int getDb() {
		return db;
	}

	public void setDb(int db) {
		pieces.addFirst(db);
		this.db = db;
	}
	
	

	public LinkedList<Integer> getPieces() {
		return pieces;
	}

	public void setPieces(LinkedList<Integer> pieces) {
		this.pieces = pieces;
	}

	public Collection<String> getWhNames() {
		return whNames;
	}

	public void setWhNames(Collection<String> whNames) {
		this.whNames = whNames;
	}

	public List<WareVo> getWares() {
		return wares;
	}

	public void setWares(List<WareVo> wares) {
		this.wares = wares;
	}

	public Collection<String> getWareNames() {
		return wareNames;
	}

	public void setWareNames(Collection<String> wareNames) {
		this.wareNames = wareNames;
	}

	public WareOrderLOcal getWareOrder() {
		return wareOrder;
	}

	public void setWareOrder(WareOrderLOcal wareOrder) {
		this.wareOrder = wareOrder;
	}

	public String getSelectedWhNames() {
		return selectedWhNames;
	}

	public void setSelectedWhNames(String selectedWhNames) {
		this.selectedWhNames = selectedWhNames;
	}

	public WarehouseServiceLocal getWarehouseService() {
		return warehouseService;
	}

	public void setWarehouseService(WarehouseServiceLocal warehouseService) {
		this.warehouseService = warehouseService;
	}

	public WareServiceLocal getWareService() {
		return wareService;
	}

	public void setWareService(WareServiceLocal wareService) {
		this.wareService = wareService;
	}

	public Collection<String> getSelectedwareNames() {
		return selectedwareNames;
	}

	public void setSelectedwareNames(Collection<String> selectedwareNames) {
		this.selectedwareNames = selectedwareNames;
	}

}
