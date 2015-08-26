package hu.neuron.java.warehouse.whWeb.controller;

import hu.neuron.java.warehouse.whBusiness.service.AdminServiceRemote;
import hu.neuron.java.warehouse.whBusiness.vo.UserVO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyAdminModel extends LazyDataModel<UserVO> {
	private static final long serialVersionUID = 1L;

	private List<UserVO> visibleUserList;

	private AdminServiceRemote adminService;

	public LazyAdminModel(AdminServiceRemote adminService) {
		super();
		this.adminService = adminService;
	}

	@Override
	public UserVO getRowData(String rowkey) {
		if (visibleUserList != null || rowkey != null) {
			for (UserVO userVO : visibleUserList) {
				if (userVO.getId().toString().equals(rowkey)) {
					return userVO;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(UserVO userVO) {
		if (userVO == null) {
			return null;
		}
		return userVO.getId();
	}

	@Override
	public List<UserVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		if (sortField == null) {
			sortField = "userName";
		}

		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		visibleUserList = adminService
				.getUsers(first / pageSize, pageSize, sortField, dir, filters);

		int dataSize = adminService.getUserCount();

		this.setRowCount(dataSize);

		return visibleUserList;
	}

	public AdminServiceRemote getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminServiceRemote adminService) {
		this.adminService = adminService;
	}

	public List<UserVO> getVisibleUserList() {
		return visibleUserList;
	}

	public void setVisibleUserList(List<UserVO> visibleUserList) {
		this.visibleUserList = visibleUserList;
	}

}
