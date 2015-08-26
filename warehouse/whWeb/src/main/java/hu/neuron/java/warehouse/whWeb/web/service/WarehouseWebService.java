package hu.neuron.java.warehouse.whWeb.web.service;

import hu.neuron.java.warehouse.whWeb.webVO.WarehouseWebVO;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WarehouseWebService {
	
	@WebMethod
	public List<WarehouseWebVO> getAllWarehouse();
}
