package hu.neuron.java.warehouse.whWeb.controller;

import hu.neuron.java.warehouse.whBusiness.service.StockReportServiceRemote;
import hu.neuron.java.warehouse.whBusiness.vo.StockHistoryVO;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyStockReportHistoryModel extends LazyDataModel<StockHistoryVO> {
	private static final long serialVersionUID = 1L;

	private List<StockHistoryVO> visibleList;

	private StockReportServiceRemote stockReportService;

	private String selectedWarehouseName = " ";

	public LazyStockReportHistoryModel(StockReportServiceRemote stockReportService) {
		super();
		this.stockReportService = stockReportService;
	}

	@Override
	public StockHistoryVO getRowData(String rowkey) {
		if (visibleList != null || rowkey != null) {
			for (StockHistoryVO stockHistoryVO : visibleList) {
				if (stockHistoryVO.getId().toString().equals(rowkey)) {
					return stockHistoryVO;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(StockHistoryVO stockHistoryVO) {
		if (stockHistoryVO == null) {
			return null;
		}
		return stockHistoryVO.getId();
	}

	@Override
	public List<StockHistoryVO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		filters.put("warehouse", selectedWarehouseName);

		if (sortField == null) {
			sortField = "ware";
		}

		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		visibleList = stockReportService.getStockHistory(first / pageSize, pageSize, sortField,
				dir, filters);

		int dataSize = stockReportService.getStockHistoryCount();

		this.setRowCount(dataSize);

		return visibleList;

	}

	public List<StockHistoryVO> getVisibleList() {
		return visibleList;
	}

	public void setVisibleList(List<StockHistoryVO> visibleList) {
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
