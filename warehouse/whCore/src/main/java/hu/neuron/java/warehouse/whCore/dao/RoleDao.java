package hu.neuron.java.warehouse.whCore.dao;

import hu.neuron.java.warehouse.whCore.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface RoleDao extends JpaRepository<Role, Long> {

	@Query("select r from Role r where r.roleName=?1")
	Role findRoleByName(String name) throws Exception;

}
