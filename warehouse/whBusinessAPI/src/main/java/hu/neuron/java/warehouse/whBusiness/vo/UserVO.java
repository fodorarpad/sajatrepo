package hu.neuron.java.warehouse.whBusiness.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String userName;

	private String password;

	private String fullName;

	private String email;

	private String phoneNumber;

	private int enabled;

	private List<RoleVO> roles = new ArrayList<>();

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", fullName=" + fullName
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", id=" + id + ", enabled"
				+ enabled + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public List<RoleVO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleVO> roles) {
		this.roles = roles;
	}

}
