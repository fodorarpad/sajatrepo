package hu.neuron.java.warehouse.whBusiness.service;

import java.util.List;

import hu.neuron.java.warehouse.whBusiness.vo.TransportDetailsVO;
import hu.neuron.java.warehouse.whBusiness.vo.TransportVO;

public interface TransportServiceRemote {

	void transportItemToWarehouse(TransportVO transportVO,
			TransportDetailsVO detailsVO);
	
	
	public List<Long> getids();

}
