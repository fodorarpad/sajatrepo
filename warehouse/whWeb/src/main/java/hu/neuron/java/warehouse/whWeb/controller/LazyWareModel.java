package hu.neuron.java.warehouse.whWeb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hu.neuron.java.warehouse.whBusiness.service.WareServiceLocal;
import hu.neuron.java.warehouse.whBusiness.vo.WareVo;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyWareModel extends LazyDataModel<WareVo> {


	private List<WareVo> visibleWareList = new ArrayList<WareVo>();

	private WareServiceLocal wareService;

	public WareServiceLocal getWareService() {
		return wareService;
	}

	public void setWareService(WareServiceLocal wareservice) {
		this.wareService = wareservice;
	}

	public LazyWareModel(WareServiceLocal wareService) {
		super();
		this.wareService = wareService;
	}

	@Override
	public WareVo getRowData(String rowkey) {
		if (visibleWareList != null || rowkey != null) {
			for (WareVo wareVo : visibleWareList) {
				if (wareVo.getId().toString().equals(rowkey)) {
					return wareVo;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(WareVo wareVo) {
		if (wareVo == null) {
			return null;
		}
		return wareVo.getId();
	}

	@Override
	public List<WareVo> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		visibleWareList.clear();
		int vSize = -1;
		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		if (sortField == null) {
			sortField = "wareName";
		}
		List<WareVo> wareList = null;
		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		wareList = wareService.getWares(first / pageSize, pageSize, sortField,
				dir, filter, filterColumnName);

		for (WareVo wareVo : wareList) {
			if (wareVo.getVisible() == 1) {
				visibleWareList.add(wareVo);

			}

		}

		int dataSize = wareService.getRowNumber();

		this.setRowCount(visibleWareList.size());

		return visibleWareList;

	}

}
