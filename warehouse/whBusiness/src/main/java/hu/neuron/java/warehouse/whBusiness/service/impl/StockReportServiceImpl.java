package hu.neuron.java.warehouse.whBusiness.service.impl;

import hu.neuron.java.warehouse.whBusiness.converter.StockConverter;
import hu.neuron.java.warehouse.whBusiness.converter.StockHistoryConverter;
import hu.neuron.java.warehouse.whBusiness.converter.TransportConverter;
import hu.neuron.java.warehouse.whBusiness.converter.TransportDetailsConverter;
import hu.neuron.java.warehouse.whBusiness.converter.WarehouseConverter;
import hu.neuron.java.warehouse.whBusiness.service.StockReportServiceRemote;
import hu.neuron.java.warehouse.whBusiness.vo.StockHistoryVO;
import hu.neuron.java.warehouse.whBusiness.vo.StockVO;
import hu.neuron.java.warehouse.whBusiness.vo.TransportVO;
import hu.neuron.java.warehouse.whBusiness.vo.WarehouseVO;
import hu.neuron.java.warehouse.whCore.dao.StockDao;
import hu.neuron.java.warehouse.whCore.dao.StockHistoryDao;
import hu.neuron.java.warehouse.whCore.dao.TransportDao;
import hu.neuron.java.warehouse.whCore.dao.WarehouseDao;
import hu.neuron.java.warehouse.whCore.entity.Stock;
import hu.neuron.java.warehouse.whCore.entity.StockHistory;
import hu.neuron.java.warehouse.whCore.entity.Transport;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "StockReportService", name = "StockReportService")
@Remote(StockReportServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class StockReportServiceImpl implements StockReportServiceRemote, Serializable {
	private static final long serialVersionUID = 1L;

	// private static final Logger logger = Logger
	// .getLogger(AdminServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	StockDao stockDao;

	@Autowired
	StockHistoryDao stockHistoryDao;

	@Autowired
	TransportDao transportDao;

	@Autowired
	WarehouseDao warehouseDao;

	@EJB
	StockConverter stockConverter;

	@EJB
	StockHistoryConverter stockHistoryConverter;

	@EJB
	TransportConverter transportConverter;

	@EJB
	TransportDetailsConverter transportDetailsConverter;

	@EJB
	WarehouseConverter warehouseConverter;

	@Override
	public WarehouseVO getWarehouseByName(String warehouseName) {
		WarehouseVO warehouseVO = null;
		try {
			warehouseVO = warehouseConverter.toVO(warehouseDao.findWarehouseByName(warehouseName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return warehouseVO;
	}

	@Override
	public int getStockRowNumber() {
		return (int) warehouseDao.count();
	}

	@Override
	public int getStockHistoryRowNumber() {
		return (int) stockHistoryDao.count();
	}

	@Override
	public int getTransportRowNumber() {
		return (int) transportDao.count();
	}

	@Override
	public List<StockVO> getStock() {
		return stockConverter.toVO(stockDao.findAll());
	}

	@Override
	public List<StockHistoryVO> getStockHistory() {
		return stockHistoryConverter.toVO(stockHistoryDao.findAll());
	}

	@Override
	public List<TransportVO> getTransports() {
		return transportConverter.toVO(transportDao.findAll());
	}

	@Override
	public List<WarehouseVO> getWarehouses() {
		return warehouseConverter.toVO(warehouseDao.findAll());
	}

	@Override
	public List<StockVO> getStock(int i, int pageSize, String sortField, int sortOrder,
			Map<String, Object> filters) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(i, pageSize, new Sort(
				new org.springframework.data.domain.Sort.Order(dir, sortField)));
		Page<Stock> entities;

		String warehouse = "", ware = "";
		for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
			try {
				String filterProperty = it.next();
				if (filterProperty.equals("warehouse")) {
					warehouse = (String) filters.get(filterProperty);
				}
				if (filterProperty.equals("ware")) {
					ware = (String) filters.get(filterProperty);
				}
			} catch (Exception e) {
			}
		}

		entities = stockDao.findByWarehouseNameStartsWithAndWareWareNameStartsWith(warehouse, ware,
				pageRequest);

		List<StockVO> ret = stockConverter.toVO(entities.getContent());

		return ret;
	}

	@Override
	public List<StockHistoryVO> getStockHistory(int i, int pageSize, String sortField,
			int sortOrder, Map<String, Object> filters) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(i, pageSize, new Sort(
				new org.springframework.data.domain.Sort.Order(dir, sortField)));
		Page<StockHistory> entities;

		String warehouse = "", ware = "";
		for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
			try {
				String filterProperty = it.next();
				if (filterProperty.equals("warehouse")) {
					warehouse = (String) filters.get(filterProperty);
				}
				if (filterProperty.equals("ware")) {
					ware = (String) filters.get(filterProperty);
				}
			} catch (Exception e) {
			}
		}

		entities = stockHistoryDao.findByWarehouseNameStartsWithAndWareWareNameStartsWith(
				warehouse, ware, pageRequest);

		List<StockHistoryVO> ret = stockHistoryConverter.toVO(entities.getContent());

		return ret;
	}

	@Override
	public List<TransportVO> getTransports(int i, int pageSize, String sortField, int sortOrder,
			String filter, String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(i, pageSize, new Sort(
				new org.springframework.data.domain.Sort.Order(dir, sortField)));
		Page<Transport> entities;

		entities = transportDao.findAll(pageRequest);

		List<TransportVO> ret = transportConverter.toVO(entities.getContent());

		return ret;
	}

	@Override
	public int getStockCount() {
		return (int) stockDao.count();
	}

	@Override
	public int getStockHistoryCount() {
		return (int) stockHistoryDao.count();
	}

	@Override
	public int getTransportCount() {
		return (int) transportDao.count();
	}
}
