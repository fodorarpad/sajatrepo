package hu.neuron.java.warehouse.whWeb.web.converter;

import hu.neuron.java.warehouse.whBusiness.vo.WareVo;
import hu.neuron.java.warehouse.whWeb.webVO.WareWebVO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

@Singleton
public class WareWebConverter {

	public WareWebVO toWebVO(WareVo vo) {
		WareWebVO web = new WareWebVO();
		web.setWareName(vo.getWareName());
		web.setItemNumber(vo.getItemNumber());
		return web;
	}

	public List<WareWebVO> toWebVo(List<WareVo> vos) {
		List<WareWebVO> webVo = new ArrayList<WareWebVO>();

		for (WareVo vo : vos) {
			webVo.add(toWebVO(vo));
		}

		return webVo;
	}

}
