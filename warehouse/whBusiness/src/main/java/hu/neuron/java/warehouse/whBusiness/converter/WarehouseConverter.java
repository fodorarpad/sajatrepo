package hu.neuron.java.warehouse.whBusiness.converter;

import hu.neuron.java.warehouse.whBusiness.vo.WarehouseVO;
import hu.neuron.java.warehouse.whCore.entity.Warehouse;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.interceptor.Interceptors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;


@Singleton
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class WarehouseConverter {
	
	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	
	@PostConstruct
	void init() {


	}

	public WarehouseVO toVO(Warehouse dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, WarehouseVO.class);
	}

	public Warehouse toEntity(WarehouseVO vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, Warehouse.class);
	}
	
	public List<WarehouseVO> toVO(List<Warehouse> dtos) {
		if (dtos == null) {
			return null;
		}
		List<WarehouseVO> vos = new ArrayList<WarehouseVO>();
		for (Warehouse dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<Warehouse> toEntity(List<WarehouseVO> vos) {
		if (vos == null) {
			return null;
		}
		List<Warehouse> dtos = new ArrayList<Warehouse>();
		for (WarehouseVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}

	

}
