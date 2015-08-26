package hu.neuron.java.warehouse.whBusiness.converter;

import hu.neuron.java.warehouse.whBusiness.vo.StockHistoryVO;
import hu.neuron.java.warehouse.whCore.entity.StockHistory;

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
public class StockHistoryConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	public StockHistoryVO toVO(StockHistory dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, StockHistoryVO.class);
	}

	public StockHistory toEntity(StockHistoryVO vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, StockHistory.class);
	}

	public List<StockHistoryVO> toVO(List<StockHistory> dtos) {
		if (dtos == null) {
			return null;
		}
		List<StockHistoryVO> vos = new ArrayList<StockHistoryVO>();
		for (StockHistory dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<StockHistory> toEntity(List<StockHistoryVO> vos) {
		if (vos == null) {
			return null;
		}
		List<StockHistory> dtos = new ArrayList<StockHistory>();
		for (StockHistoryVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}
}
