package hu.neuron.java.warehouse.whBusiness.service;

import hu.neuron.java.warehouse.whBusiness.vo.UserVO;

public interface LoginServiceRemote {

	UserVO findUserAndRolesByName(String username) throws Exception;

}
