package hu.neuron.java.warehouse.whBusiness.service.impl;

import java.util.List;

import hu.neuron.java.warehouse.whBusiness.converter.StockConverter;
import hu.neuron.java.warehouse.whBusiness.converter.WarehouseConverter;
import hu.neuron.java.warehouse.whBusiness.service.WareOrderLOcal;
import hu.neuron.java.warehouse.whBusiness.service.WareOrderRemote;
import hu.neuron.java.warehouse.whBusiness.vo.StockVO;
import hu.neuron.java.warehouse.whCore.dao.StockDao;
import hu.neuron.java.warehouse.whCore.dao.WarehouseDao;
import hu.neuron.java.warehouse.whCore.entity.Stock;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "WareOrderService", name = "WareOrderService")
@Local(WareOrderLOcal.class)
@Remote(WareOrderRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class WareOrderServiceImpl implements WareOrderLOcal, WareOrderRemote {

	@Autowired
	private WarehouseDao warehouseDao;

	@Autowired
	private StockDao stockDao;

	@EJB
	private WarehouseConverter warehouseConverter;

	@EJB
	private StockConverter stockConverter;

	public void order(StockVO order) {
		StockVO asd = new StockVO();
		try {
			
			asd = stockConverter.toVO(stockDao.findStockByWarehouseIdandWareId(
					order.getWarehouse().getId(), order.getWare().getId()));
			
			stockDao.updateStock(	order.getWarehouse().getId(), order.getWare().getId(),asd.getPiece()+order.getPiece());
		} catch (Exception e) {
			stockDao.save(stockConverter.toEntity(order));
		}
	}

}
