package hu.neuron.java.warehouse.whBusiness.service;

import hu.neuron.java.warehouse.whBusiness.vo.UserVO;

public interface RegistrationServiceRemote {

	public boolean registrationUser(UserVO userVO) throws Exception;

	public UserVO getUserByName(String user);

	public UserVO getUserByEmail(String email);

}
