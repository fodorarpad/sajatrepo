package hu.neuron.java.warehouse.whWeb.controller;

import hu.neuron.java.warehouse.whBusiness.service.RegistrationServiceRemote;
import hu.neuron.java.warehouse.whBusiness.vo.UserVO;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ViewScoped
@ManagedBean(name = "registrationController")
public class RegistrationController implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userName = "";

	private String password1 = "";

	private String password2 = "";

	private String fullName = "";

	private String email = "";

	private String phoneNumber = "";

	@EJB(name = "RegistrationService", mappedName = "RegistrationService")
	private RegistrationServiceRemote registrationService;

	public String addUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (!password1.equals(getPassword2())) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
						"Password not match"));
				return null;
			}else if (registrationService.getUserByName(userName) != null) {
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error!",
						"User name already in used"));
				return null;
			}else if (registrationService.getUserByEmail(email) != null) {
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error!",
						"Email already in used"));
				return null;
			}

			UserVO userVO = new UserVO();

			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String encPassword = bCryptPasswordEncoder.encode(password1);

			userVO.setUserName(userName);
			userVO.setPassword(encPassword);
			userVO.setFullName(fullName);
			userVO.setEmail(email);
			userVO.setPhoneNumber(phoneNumber);

			if (registrationService.registrationUser(userVO)) {
				context.getExternalContext().getFlash().setKeepMessages(true);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"Registration successful you can log in now"));
				return "/public/login.xhtml?faces-redirect=true";
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
		return null;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
