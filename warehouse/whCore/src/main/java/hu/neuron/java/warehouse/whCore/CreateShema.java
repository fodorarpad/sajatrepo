package hu.neuron.java.warehouse.whCore;

import hu.neuron.java.warehouse.whCore.dao.RoleDao;
import hu.neuron.java.warehouse.whCore.dao.UserDao;
import hu.neuron.java.warehouse.whCore.entity.Role;
import hu.neuron.java.warehouse.whCore.entity.User;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CreateShema {
	private static final Logger logger = Logger.getLogger(CreateShema.class);

	@Autowired
	public RoleDao roleDAO;

	@Autowired
	public UserDao userDAO;

	public void insertRoles() {
		Role dto = null;
		try {
			if (roleDAO.findRoleByName("ROLE_ADMIN") == null) {
				dto = new Role();
				dto.setRoleName("ROLE_ADMIN");
				roleDAO.save(dto);
			}
			if (roleDAO.findRoleByName("ROLE_USER") == null) {
				dto = new Role();
				dto.setRoleName("ROLE_USER");
				roleDAO.save(dto);
			}
			if (roleDAO.findRoleByName("ROLE_MANAGER") == null) {
				dto = new Role();
				dto.setRoleName("ROLE_MANAGER");
				roleDAO.save(dto);
			}
			if (roleDAO.findRoleByName("ROLE_STOREKEEPER") == null) {
				dto = new Role();
				dto.setRoleName("ROLE_STOREKEEPER");
				roleDAO.save(dto);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void insertUsersAndAddRole() {
		try {
			if (userDAO.findUserByName("admin") == null) {
				List<Role> roles = new ArrayList<Role>();
				Role role;
				User dto = new User();
				dto.setUserName("admin");
				dto.setPassword("$2a$10$pNotpuCVFsm6K9Zi3cgUI.b3YsgDdDFD3DEsbWu3pYiVBXbV3sg7G");
				dto.setEmail("admin@admin.admin");
				dto.setEnabled(1);
				dto.setFullName("Adminus Maximus");
				dto.setPhoneNumber("0620321456789");

				role = roleDAO.findRoleByName("ROLE_ADMIN");
				roles.add(role);
				role = roleDAO.findRoleByName("ROLE_USER");
				roles.add(role);

				dto.setRoles(roles);
				userDAO.save(dto);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
