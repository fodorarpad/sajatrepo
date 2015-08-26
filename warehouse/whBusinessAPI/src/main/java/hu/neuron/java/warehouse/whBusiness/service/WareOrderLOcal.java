package hu.neuron.java.warehouse.whBusiness.service;

import hu.neuron.java.warehouse.whBusiness.vo.StockVO;
import hu.neuron.java.warehouse.whBusiness.vo.WareVo;
import hu.neuron.java.warehouse.whBusiness.vo.WarehouseVO;

public interface WareOrderLOcal {

	public void order(StockVO order);
	
}
