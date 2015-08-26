package hu.neuron.java.warehouse.whWeb.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;

import hu.neuron.java.warehouse.whBusiness.service.WarehouseServiceLocal;
import hu.neuron.java.warehouse.whWeb.web.converter.WarehouseWebConverter;
import hu.neuron.java.warehouse.whWeb.web.service.WarehouseWebService;
import hu.neuron.java.warehouse.whWeb.webVO.WarehouseWebVO;

@WebService(endpointInterface = "hu.neuron.java.warehouse.whWeb.web.service.WarehouseWebService")
public class WarehouseWebServiceImpl implements WarehouseWebService {
	
	@EJB(name = "WarehouseService")
	private WarehouseServiceLocal warehouseService;
	
	@EJB
	private WarehouseWebConverter warehouseWebConverter;

	@Override
	public List<WarehouseWebVO> getAllWarehouse() {
		List<WarehouseWebVO> res = new ArrayList<WarehouseWebVO>();
		res = warehouseWebConverter.toWebVo(warehouseService.findAll());
		return res;
	}
}
