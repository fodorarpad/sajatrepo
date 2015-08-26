package hu.neuron.java.warehouse.whBusiness.service.impl;

import hu.neuron.java.warehouse.whBusiness.converter.RoleConverter;
import hu.neuron.java.warehouse.whBusiness.converter.UserConverter;
import hu.neuron.java.warehouse.whBusiness.service.LoginServiceRemote;
import hu.neuron.java.warehouse.whBusiness.vo.UserVO;
import hu.neuron.java.warehouse.whCore.dao.RoleDao;
import hu.neuron.java.warehouse.whCore.dao.UserDao;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(name = "LoginService", mappedName = "LoginService")
@Remote(LoginServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class LoginServiceImpl implements LoginServiceRemote, Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@EJB
	RoleConverter roleConverter;

	@EJB
	UserConverter userConverter;

	@Override
	public UserVO findUserAndRolesByName(String username) throws Exception {
		UserVO userVO;
		userVO=userConverter.toVO(userDao.findUserByName(username));
		userVO.setRoles(roleConverter.toVO(userDao.findUserByName(username).getRoles()));		
		return userVO;
	}

}
