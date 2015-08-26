package hu.neuron.java.warehouse.whBusiness.service;

import hu.neuron.java.warehouse.whBusiness.vo.WarehouseVO;

import java.util.List;

public interface WarehouseServiceRemote {
	
	public void save(WarehouseVO warehouseVo);
	
	public void dalete(WarehouseVO warehouseVo);
	
	public void update(WarehouseVO warehouseVo);
	
	public void addUserToWarehouse(String userName, String warehouseId) throws Exception;
	
	public WarehouseVO findWarehouseWarehouseId(String warehouseId) throws Exception;
	
	
	public WarehouseVO findWarehouseByName(String name) throws Exception;

	public List<WarehouseVO> getWarehouse(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName);
	
	public List<WarehouseVO> findAll();
	
	public int getRowNumber();

}
