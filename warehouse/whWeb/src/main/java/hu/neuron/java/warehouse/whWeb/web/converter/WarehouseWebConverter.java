package hu.neuron.java.warehouse.whWeb.web.converter;

import hu.neuron.java.warehouse.whBusiness.vo.WarehouseVO;
import hu.neuron.java.warehouse.whWeb.webVO.WarehouseWebVO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.interceptor.Interceptors;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Singleton
public class WarehouseWebConverter {

	public WarehouseWebVO toWebVO(WarehouseVO vo) {
		WarehouseWebVO web = new WarehouseWebVO();
		web.setName(vo.getName());
		web.setWarehouseId(vo.getWarehouseId());
		return web;
	}

	public List<WarehouseWebVO> toWebVo(List<WarehouseVO> vos) {
		List<WarehouseWebVO> webVo = new ArrayList<WarehouseWebVO>();

		for (WarehouseVO vo : vos) {
			webVo.add(toWebVO(vo));
		}

		return webVo;
	}

}
