package hu.neuron.java.warehouse.whBusiness.converter;

import hu.neuron.java.warehouse.whBusiness.vo.StockVO;
import hu.neuron.java.warehouse.whCore.entity.Stock;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.interceptor.Interceptors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Singleton
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class StockConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public StockVO toVO(Stock dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, StockVO.class);
	}

	public Stock toEntity(StockVO vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, Stock.class);
	}

	public List<StockVO> toVO(List<Stock> dtos) {
		if (dtos == null) {
			return null;
		}
		List<StockVO> vos = new ArrayList<StockVO>();
		for (Stock dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<Stock> toEntity(List<StockVO> vos) {
		if (vos == null) {
			return null;
		}
		List<Stock> dtos = new ArrayList<Stock>();
		for (StockVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}
}
