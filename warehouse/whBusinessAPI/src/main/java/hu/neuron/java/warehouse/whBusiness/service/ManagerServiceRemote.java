package hu.neuron.java.warehouse.whBusiness.service;

import hu.neuron.java.warehouse.whBusiness.vo.ManagerVO;
import hu.neuron.java.warehouse.whBusiness.vo.UserVO;
import hu.neuron.java.warehouse.whBusiness.vo.WarehouseVO;

import java.util.List;

public interface ManagerServiceRemote {
	public void addManager(ManagerVO manager);
	
	public void deleteManager(ManagerVO manager);
	
	public List<WarehouseVO> getWarehouseByUsderName(String userName);

	List<UserVO> getUserByWarehouseId(String WarehouseId);
}
