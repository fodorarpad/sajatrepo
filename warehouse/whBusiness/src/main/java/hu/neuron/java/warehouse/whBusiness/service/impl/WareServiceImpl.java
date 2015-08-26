package hu.neuron.java.warehouse.whBusiness.service.impl;

import hu.neuron.java.warehouse.whBusiness.converter.StockConverter;
import hu.neuron.java.warehouse.whBusiness.converter.WareConverter;
import hu.neuron.java.warehouse.whBusiness.converter.WarehouseConverter;
import hu.neuron.java.warehouse.whBusiness.service.WareServiceLocal;
import hu.neuron.java.warehouse.whBusiness.service.WareServiceRemote;
import hu.neuron.java.warehouse.whBusiness.vo.StockVO;
import hu.neuron.java.warehouse.whBusiness.vo.WareVo;
import hu.neuron.java.warehouse.whBusiness.vo.WarehouseVO;
import hu.neuron.java.warehouse.whCore.dao.StockDao;
import hu.neuron.java.warehouse.whCore.dao.WareDao;
import hu.neuron.java.warehouse.whCore.dao.WarehouseDao;
import hu.neuron.java.warehouse.whCore.entity.Ware;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "WareService", name = "WareService")
@Local(WareServiceLocal.class)
@Remote(WareServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class WareServiceImpl implements WareServiceLocal, WareServiceRemote,
		Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger
			.getLogger(WareServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	WareDao wareDao;

	// @EJB
	// WareServiceLocal wareService;

	@EJB
	WareConverter wareConverter;

	@EJB
	WarehouseConverter warehouseConverter;

	@Autowired
	WarehouseDao warehouseDao;

	@Autowired
	StockDao stockDao;

	@EJB
	StockConverter stockConverter;

	@Override
	public WareVo findWareByName(String ware) {
		WareVo vo = null;
		try {
			vo = wareConverter.toVO(wareDao.findByWareName(ware));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public WareVo setUpWares(WareVo vo) throws Exception {

		return null;
	}

	@Override
	public int getRowNumber() {
		return (int) wareDao.count();
	}

	@Override
	public List<WareVo> getWares() {
		return wareConverter.toVO(wareDao.findAll());

	}

	@Override
	public void updateWare(WareVo wareVo) {
		wareDao.save(wareConverter.toEntity(wareVo));

	}

	@Override
	public List<WareVo> getWares(int i, int pageSize, String sortField,
			int sortOrder, String filter, String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;
//		Integer filternum=Integer.parseInt(filter);
		PageRequest pageRequest = new PageRequest(i, pageSize, new Sort(
				new org.springframework.data.domain.Sort.Order(dir, sortField)));
		Page<Ware> entities;
		
		if (filter.length() != 0 && filterColumnName.equals("wareName")) {
			entities = wareDao.findByWareNameStartsWith(filter, pageRequest);
		} 
		else if (filter.length() != 0 && filterColumnName.equals("itemNumber")) {
			entities = wareDao.findByItemNumberStartsWith(filter, pageRequest);
		} 
		else if (filter.length() != 0 && filterColumnName.equals("description")) {
			entities = wareDao.findByDescriptionStartsWith(filter, pageRequest);
		} 
		else {
			entities = wareDao.findAll(pageRequest);

		}

		List<WareVo> ret = wareConverter.toVO(entities.getContent());

		return ret;
	}

	@Override
	public int getRoleCount() {
		return (int) wareDao.count();
	}

	@Override
	public void saveWare(WareVo wareVo) {
		wareDao.save(wareConverter.toEntity(wareVo));

	}

	@Override
	public void removeWare(WareVo selectedWare) {
		wareDao.delete(selectedWare.getId());

	}

	@Override
	public int getNumByWarehouseAndWareId(String warehouseId, Long wareId) {
		WarehouseVO wh = new WarehouseVO();
		int out = 0;
		try {
			wh = warehouseConverter.toVO((warehouseDao
					.findWarehouseByWarehouseId(warehouseId)));

			List<StockVO> wares = stockConverter.toVO(stockDao
					.findStockByWarehouseId(wh.getId()));

			for (StockVO stockVO : wares) {
				if (stockVO.getWare().getId() == wareId) {
					out = stockVO.getPiece();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return out;
	}

	@Override
	public void decreaseNumberByWhWareAndNum(String warehouseId, Long wareId,
			int num) {
		WarehouseVO wh = new WarehouseVO();
		try {

			wh = warehouseConverter.toVO((warehouseDao
					.findWarehouseByWarehouseId(warehouseId)));

			StockVO ware = stockConverter.toVO(stockDao
					.findStockByWarehouseIdandWareId(wh.getId(), wareId));

			if (ware.getPiece() - num >= 0) {
				stockDao.updateStock(wh.getId(), wareId, ware.getPiece() - num);
			} else {
				logger.error("Too much number of items.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Integer> findwareAndPiecesByWarehouseId(
			String warehouseId) {
		WarehouseVO wh = new WarehouseVO();
		Map<String, Integer> res = new HashMap<String, Integer>();
		try {
			wh = warehouseConverter.toVO((warehouseDao
					.findWarehouseByWarehouseId(warehouseId)));

			List<StockVO> wares = stockConverter.toVO(stockDao
					.findStockByWarehouseId(wh.getId()));

			for (StockVO stockVO : wares) {
				res.put(stockVO.getWare().getWareName(), stockVO.getPiece());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

}
