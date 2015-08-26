package hu.neuron.java.warehouse.whBusiness.converter;

import hu.neuron.java.warehouse.whBusiness.vo.TransportDetailsVO;
import hu.neuron.java.warehouse.whCore.entity.TransportDetails;

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
public class TransportDetailsConverter {
	
	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	private static final Logger logger = Logger.getLogger(TransportDetailsConverter.class);

	@PostConstruct
	void init() {
		logger.info("TransportDetailsConverter!!!");
	}

	public TransportDetailsVO toVO(TransportDetails dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, TransportDetailsVO.class);
	}

	public TransportDetails toEntity(TransportDetailsVO vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, TransportDetails.class);
	}
	
	public List<TransportDetailsVO> toVO(List<TransportDetails> dtos) {
		if (dtos == null) {
			return null;
		}
		List<TransportDetailsVO> vos = new ArrayList<TransportDetailsVO>();
		for (TransportDetails dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<TransportDetails> toEntity(List<TransportDetailsVO> vos) {
		if (vos == null) {
			return null;
		}
		List<TransportDetails> dtos = new ArrayList<TransportDetails>();
		for (TransportDetailsVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}

}
