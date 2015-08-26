package hu.neuron.java.warehouse.whBusiness.converter;



import hu.neuron.java.warehouse.whBusiness.vo.WareVo;
import hu.neuron.java.warehouse.whCore.entity.Ware;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;


@Singleton
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class WareConverter {
	private static final Logger logger = Logger.getLogger(WareConverter.class);
	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	@PostConstruct
	void init() {
	
		logger.debug("WareConverter init");
	}
	
	public WareVo toVO(Ware dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, WareVo.class);
	}

	public Ware toEntity(WareVo vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, Ware.class);
	}

	public List<WareVo> toVO(List<Ware> dtos) {
		if (dtos == null) {
			return null;
		}
		List<WareVo> vos = new ArrayList<WareVo>();
		for (Ware dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<Ware> toEntity(List<WareVo> vos) {
		if (vos == null) {
			return null;
		}
		List<Ware> dtos = new ArrayList<Ware>();
		for (WareVo vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}

}
