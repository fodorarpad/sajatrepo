package hu.neuron.java.warehouse.whWeb.web.service.impl;

import hu.neuron.java.warehouse.whBusiness.service.WareServiceLocal;
import hu.neuron.java.warehouse.whBusiness.service.WarehouseServiceLocal;
import hu.neuron.java.warehouse.whWeb.web.converter.WareWebConverter;
import hu.neuron.java.warehouse.whWeb.web.converter.WarehouseWebConverter;
import hu.neuron.java.warehouse.whWeb.web.service.WareWebService;
import hu.neuron.java.warehouse.whWeb.webVO.WareWebVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.jws.WebService;

@WebService(endpointInterface = "hu.neuron.java.warehouse.whWeb.web.service.WareWebService")
public class WareWebServiceImpl implements WareWebService {
	
	@EJB(name = "WareService")
	WareServiceLocal wareService;
	
	@EJB
	WareWebConverter wareWebConverter;
	
	@EJB(name = "WarehouseService")
	WarehouseServiceLocal warehouseService;
	
	@EJB
	WarehouseWebConverter warehouseWebConverter;

	/**
	 * Terméktípusok listája
	 */
	@Override
	public List<WareWebVO> getAllWares() {
		List<WareWebVO> out = new ArrayList<>();
		out = wareWebConverter.toWebVo(wareService.getWares());
		return out;
	}

	/**
	 * Egy paraméterül kapott raktár alapján az elérhető terméktípusok listája
	 * és darabszáma
	 */
	@Override
	public Map<String, Integer> getWaresNumbers(String warehouseId) {
		Map<String, Integer> out = new HashMap<String, Integer>();
		out = wareService.findwareAndPiecesByWarehouseId(warehouseId);
        return out;
	}

	/**
	 * Egy paraméterül kapott raktár és terméktípus alapján visszaadni a
	 * darabszámot
	 */
	@Override
	public int getNumberOfWares(String warehouseId, Long wareId) {
		return wareService.getNumByWarehouseAndWareId(warehouseId, wareId);
	}

	/**
	 * Egy paraméterül kapott raktár, terméktípus és darabszám alapján
	 * csökkenteni a darabszámot az adott raktárban az adott terméktípusból.
	 */
	@Override
	public void decreaseNumberOfWares(String warehouseId, Long wareId, int number) {
		wareService.decreaseNumberByWhWareAndNum(warehouseId, wareId, number);
	}

}
