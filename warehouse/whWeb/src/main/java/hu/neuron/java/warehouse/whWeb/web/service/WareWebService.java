package hu.neuron.java.warehouse.whWeb.web.service;

import hu.neuron.java.warehouse.whWeb.webVO.WareWebVO;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WareWebService {

	/**
	 * Terméktípusok listája
	 */
	@WebMethod
	public List<WareWebVO> getAllWares();

	/**
	 * Egy paraméterül kapott raktár alapján az elérhető terméktípusok listája
	 * és darabszáma
	 */
	@WebMethod
	public Map<String, Integer> getWaresNumbers(String warehouseId);

	/**
	 * Egy paraméterül kapott raktár és terméktípus alapján visszaadni a
	 * darabszámot
	 */
	@WebMethod
	public int getNumberOfWares(String warehouseId, Long wareId);

	/**
	 * Egy paraméterül kapott raktár, terméktípus és darabszám alapján
	 * csökkenteni a darabszámot az adott raktárban az adott terméktípusból.
	 */
	@WebMethod
	public void decreaseNumberOfWares(String warehouseId, Long wareId, int number);
	
}
