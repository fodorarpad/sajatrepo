package hu.neuron.java.warehouse.whBusiness.service;

import hu.neuron.java.warehouse.whBusiness.vo.RoleVO;
import hu.neuron.java.warehouse.whBusiness.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface AdminServiceRemote {

	public UserVO getUserByName(String userName);

	public int getRowNumber();

	public List<UserVO> getUsers();

	public List<UserVO> getUsers(int i, int pageSize, String sortField, int sortOrder,
			Map<String, Object> filters);

	public int getUserCount();

	public Boolean updateUser(UserVO userVO) throws Exception;

	public List<RoleVO> getRoles();

	public RoleVO getRoleByName(String roleName);
}
