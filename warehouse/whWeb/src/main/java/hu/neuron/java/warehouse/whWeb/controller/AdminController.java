package hu.neuron.java.warehouse.whWeb.controller;

import hu.neuron.java.warehouse.whBusiness.service.AdminServiceRemote;
import hu.neuron.java.warehouse.whBusiness.vo.RoleVO;
import hu.neuron.java.warehouse.whBusiness.vo.UserVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

@ViewScoped
@ManagedBean(name = "adminController")
public class AdminController implements Serializable {
	private static final long serialVersionUID = 1L;

	private LazyAdminModel lazyAdminModel;

	@EJB(beanName = "AdminService")
	private AdminServiceRemote adminService;

	private UserVO selectedUser;

	private Boolean enabled;

	private DualListModel<String> roles;

	@PostConstruct
	public void init() {
		setLazyAdminModel(new LazyAdminModel(getAdminService()));
	}

	public void onRowSelect(SelectEvent event) {
		selectedUser = (UserVO) event.getObject();
		enabled = selectedUser.getEnabled() == 1 ? true : false;

		List<RoleVO> roleVOs = adminService.getRoles();
		List<RoleVO> selectedRoleVOs = selectedUser.getRoles();

		List<String> roleNames = new ArrayList<String>();
		List<String> selectedRoleNames = new ArrayList<String>();

		for (RoleVO roleVO : roleVOs) {
			roleNames.add(roleVO.getRoleName());
		}
		for (RoleVO roleVO : selectedRoleVOs) {
			selectedRoleNames.add(roleVO.getRoleName());
		}
		for (String name : selectedRoleNames) {
			roleNames.remove(name);
		}

		roles = new DualListModel<String>(roleNames, selectedRoleNames);
	}

	public String updateUser() {
		if (!selectedUser.getUserName().equals("admin")) {
			List<RoleVO> selectedRoleVOs = new ArrayList<RoleVO>();

			for (String roleName : roles.getTarget()) {
				selectedRoleVOs.add(adminService.getRoleByName(roleName));
			}

			selectedUser.setEnabled(enabled == true ? 1 : 0);
			selectedUser.setRoles(selectedRoleVOs);
			try {
				if (adminService.updateUser(selectedUser)) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Update Success"));
					selectedUser = null;
					roles = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
			}
			return null;
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
							"Admin can't be changed"));
			selectedUser = null;
			roles = null;
			return null;
		}

	}

	public void cancelSelect() {
		selectedUser = null;
		roles = null;
	}

	public LazyAdminModel getLazyAdminModel() {
		return lazyAdminModel;
	}

	public void setLazyAdminModel(LazyAdminModel lazyAdminModel) {
		this.lazyAdminModel = lazyAdminModel;
	}

	public AdminServiceRemote getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminServiceRemote adminService) {
		this.adminService = adminService;
	}

	public UserVO getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserVO selectedUser) {
		this.selectedUser = selectedUser;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public DualListModel<String> getRoles() {
		return roles;
	}

	public void setRoles(DualListModel<String> roles) {
		this.roles = roles;
	}

}
