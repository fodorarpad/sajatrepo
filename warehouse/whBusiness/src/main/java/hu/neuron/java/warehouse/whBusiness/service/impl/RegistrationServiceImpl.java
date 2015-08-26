package hu.neuron.java.warehouse.whBusiness.service.impl;

import hu.neuron.java.warehouse.whBusiness.converter.RoleConverter;
import hu.neuron.java.warehouse.whBusiness.converter.UserConverter;
import hu.neuron.java.warehouse.whBusiness.service.RegistrationServiceRemote;
import hu.neuron.java.warehouse.whBusiness.vo.RoleVO;
import hu.neuron.java.warehouse.whBusiness.vo.UserVO;
import hu.neuron.java.warehouse.whCore.dao.RoleDao;
import hu.neuron.java.warehouse.whCore.dao.UserDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(name = "RegistrationService", mappedName = "RegistrationService")
@Remote(RegistrationServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class RegistrationServiceImpl implements RegistrationServiceRemote, Serializable {
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
	public boolean registrationUser(UserVO userVO) throws Exception {
		List<RoleVO> roleVOs = new ArrayList<RoleVO>();
		RoleVO roleVO = roleConverter.toVO(roleDao.findRoleByName("ROLE_USER"));
		roleVOs.add(roleVO);
		userVO.setRoles(roleVOs);
		userVO.setEnabled(1);
		userDao.save(userConverter.toEntity(userVO));
		return true;
	}

	@Override
	public UserVO getUserByName(String user) {
		UserVO userVO = null;
		try {
			userVO = userConverter.toVO(userDao.findUserByName(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVO;
	}

	@Override
	public UserVO getUserByEmail(String email) {
		UserVO userVO = null;
		try {
			userVO = userConverter.toVO(userDao.findUserByEmail(email));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVO;
	}

}
