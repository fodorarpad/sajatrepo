package hu.neuron.java.warehouse.whBusiness.converter;

import hu.neuron.java.warehouse.whBusiness.vo.TransportVO;
import hu.neuron.java.warehouse.whCore.entity.Transport;

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
public class TransportConverter {
	
	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	private static final Logger logger = Logger.getLogger(TransportConverter.class);

	@PostConstruct
	void init() {
		logger.info("TransportConverter!!!");
	}

	public TransportVO toVO(Transport dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, TransportVO.class);
	}

	public Transport toEntity(TransportVO vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, Transport.class);
	}
	
	public List<TransportVO> toVO(List<Transport> dtos) {
		if (dtos == null) {
			return null;
		}
		List<TransportVO> vos = new ArrayList<TransportVO>();
		for (Transport dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<Transport> toEntity(List<TransportVO> vos) {
		if (vos == null) {
			return null;
		}
		List<Transport> dtos = new ArrayList<Transport>();
		for (TransportVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}

}
