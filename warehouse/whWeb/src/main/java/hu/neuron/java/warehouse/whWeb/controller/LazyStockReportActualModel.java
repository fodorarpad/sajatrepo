package hu.neuron.java.warehouse.whWeb.controller;

import hu.neuron.java.warehouse.whBusiness.service.StockReportServiceRemote;
import hu.neuron.java.warehouse.whBusiness.vo.StockVO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyStockReportActualModel extends LazyDataModel<StockVO> {
	private static final long serialVersionUID = 1L;

	private List<StockVO> visibleList;

	private StockReportServiceRemote stockReportService;

	private String selectedWarehouseName = " ";

	public LazyStockReportActualModel(StockReportServiceRemote stockReportService) {
		super();
		this.stockReportService = stockReportService;
	}

	@Override
	public StockVO getRowData(String rowkey) {
		if (visibleList != null || rowkey != null) {
			for (StockVO stockVO : visibleList) {
				if (stockVO.getId().toString().equals(rowkey)) {
					return stockVO;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(StockVO stockVO) {
		if (stockVO == null) {
			return null;
		}
		return stockVO.getId();
	}

	@Override
	public List<StockVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		filters.put("warehouse", selectedWarehouseName);

		if (sortField == null) {
			sortField = "ware";
		}

		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		visibleList = stockReportService.getStock(first / pageSize, pageSize, sortField, dir,
				filters);

		int dataSize = stockReportService.getStockCount();

		this.setRowCount(dataSize);

		return visibleList;
	}

	public List<StockVO> getVisibleList() {
		return visibleList;
	}

	public void setVisibleList(List<StockVO> visibleList) {
		this.visibleList = visibleList;
	}

	public StockReportServiceRemote getStockReportService() {
		return stockReportService;
	}

	public void setStockReportService(StockReportServiceRemote stockReportService) {
		this.stockReportService = stockReportService;
	}

	public String getSelectedWarehouseName() {
		return selectedWarehouseName;
	}

	public void setSelectedWarehouseName(String selectedWarehouseName) {
		this.selectedWarehouseName = selectedWarehouseName;
	}
}
