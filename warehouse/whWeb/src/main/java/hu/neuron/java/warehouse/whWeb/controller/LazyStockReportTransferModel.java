package hu.neuron.java.warehouse.whWeb.controller;

import hu.neuron.java.warehouse.whBusiness.service.StockReportServiceRemote;
import hu.neuron.java.warehouse.whBusiness.vo.TransportVO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyStockReportTransferModel extends LazyDataModel<TransportVO> {
	private static final long serialVersionUID = 1L;

	private List<TransportVO> visibleList;

	private StockReportServiceRemote stockReportService;

	public LazyStockReportTransferModel(StockReportServiceRemote stockReportService) {
		super();
		this.stockReportService = stockReportService;
	}

	@Override
	public TransportVO getRowData(String rowkey) {
//		if (visibleList != null || rowkey != null) {
//			for (TransportVO transportVO : visibleList) {
//				if (transportVO.getId().toString().equals(rowkey)) {
//					return transportVO;
//				}
//			}
//		}
		return null;
	}

	@Override
	public Object getRowKey(TransportVO transportVO) {
//		if (transportVO == null) {
//			return null;
//		}
//		return transportVO.getId();
		return null;
	}

	@Override
	public List<TransportVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		if (sortField == null) {
			sortField = "fromWarehouse";
		}

		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		visibleList = stockReportService.getTransports(first / pageSize, pageSize, sortField, dir, filter,
				filterColumnName);

		int dataSize = stockReportService.getTransportCount();

		this.setRowCount(dataSize);

		return visibleList;

	}



}
