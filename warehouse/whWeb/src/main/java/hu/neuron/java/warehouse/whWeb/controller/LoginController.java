package hu.neuron.java.warehouse.whWeb.controller;

import hu.neuron.java.warehouse.whBusiness.service.LoginServiceRemote;
import hu.neuron.java.warehouse.whBusiness.vo.RoleVO;
import hu.neuron.java.warehouse.whBusiness.vo.UserVO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("loginController")
@ManagedBean(name = "loginController")
public class LoginController implements UserDetailsService {

	@EJB(name = "LoginService", mappedName = "LoginService")
	private LoginServiceRemote loginService;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		UserVO user;
		try {
			user = loginService.findUserAndRolesByName(username);

			if (user == null) {
				throw new UsernameNotFoundException(username);
			}
			List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

			return buildUserForAuthentication(user, authorities);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(e.getMessage());
		}

	}

	private User buildUserForAuthentication(UserVO user, List<GrantedAuthority> authorities) {
		return new User(user.getUserName(), user.getPassword(), user.getEnabled() == 1 ? true
				: false, true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<RoleVO> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		for (RoleVO userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRoleName()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}