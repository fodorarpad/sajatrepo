package hu.neuron.java.warehouse.whWeb.controller;

import hu.neuron.java.warehouse.whBusiness.service.UserSelfCareServiceRemote;
import hu.neuron.java.warehouse.whBusiness.vo.UserVO;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ViewScoped
@ManagedBean(name = "userSelfCareController")
public class UserSelfCareController implements Serializable {
	private static final long serialVersionUID = 1L;

	private UserVO currentUser = new UserVO();
	private String password1 = null;
	private String password2 = null;
	private boolean newPasswordCheck = false;

	@EJB(name = "UserSelfCareService", mappedName = "UserSelfCareService")
	private UserSelfCareServiceRemote userSelfCareService;

	@PostConstruct
	public void init() {
		currentUser = userSelfCareService.getUserByName(SecurityContextHolder.getContext()
				.getAuthentication().getName());
	}

	public String updateUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (newPasswordCheck && !password1.equals(getPassword2())) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
						"Password not match"));
				return null;
			} else if (userSelfCareService.getUserByEmail(currentUser.getEmail()) != null
					&& !userSelfCareService.getUserByEmail(currentUser.getEmail()).getUserName()
							.equals(currentUser.getUserName())) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
						"Email already in used"));
				return null;
			}

			if (newPasswordCheck && !password1.equals("")) {
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				String encPassword = bCryptPasswordEncoder.encode(password1);
				currentUser.setPassword(encPassword);
			}

			if (userSelfCareService.updateUser(currentUser)) {
				context.getExternalContext().getFlash().setKeepMessages(true);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"Update successful"));
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
		return null;
	}

	public UserVO getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserVO currentUser) {
		this.currentUser = currentUser;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public UserSelfCareServiceRemote getUserSelfCareService() {
		return userSelfCareService;
	}

	public void setUserSelfCareService(UserSelfCareServiceRemote userSelfCareService) {
		this.userSelfCareService = userSelfCareService;
	}

	public boolean isNewPasswordCheck() {
		return newPasswordCheck;
	}

	public void setNewPasswordCheck(boolean newPasswordCheck) {
		this.newPasswordCheck = newPasswordCheck;
	}

}
