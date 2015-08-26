package hu.neuron.java.warehouse.whCore.dao;

import hu.neuron.java.warehouse.whCore.entity.Role;
import hu.neuron.java.warehouse.whCore.entity.User;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test-core.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class UserDaoTest {

	private static final Logger logger = Logger.getLogger(UserDaoTest.class);

	private static User user;

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Test
	public void test1Save() {
		try {
			for (int i = 0; i < 10; i++) {
				user = new User();
				user.setUserName("asd" + i);
				user.setPassword("asd" + i);
				user.setEnabled(1);
				user.setEmail("asd" + i + "@asd.asd");
				user.setFullName("asd asd" + i);
				user.setPhoneNumber("5436575" + i);
				ArrayList<Role> roles = new ArrayList<Role>();
				roles.add(roleDao.findRoleByName("USER"));
				user.setRoles(roles);
				user = userDao.save(user);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2() {
		try {
			user = userDao.findUserByName("asd2");			
			user.setRoles(userDao.findUserByName("asd2").getRoles());
			System.out.println(user+"|| "+user.getRoles());
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
