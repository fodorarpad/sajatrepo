package hu.neuron.java.warehouse.whBusiness.converter;

import hu.neuron.java.warehouse.whBusiness.vo.ManagerVO;
import hu.neuron.java.warehouse.whBusiness.vo.StockVO;
import hu.neuron.java.warehouse.whCore.entity.Manager;
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
public class ManagerConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public ManagerVO toVO(Manager dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, ManagerVO.class);
	}

	public Manager toEntity(ManagerVO vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, Manager.class);
	}

	public List<ManagerVO> toVO(List<Manager> dtos) {
		if (dtos == null) {
			return null;
		}
		List<ManagerVO> vos = new ArrayList<ManagerVO>();
		for (Manager dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<Manager> toEntity(List<ManagerVO> vos) {
		if (vos == null) {
			return null;
		}
		List<Manager> dtos = new ArrayList<Manager>();
		for (ManagerVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}
}
