package hu.neuron.java.warehouse.whCore.dao;

import java.util.List;

import hu.neuron.java.warehouse.whCore.entity.Manager;
import hu.neuron.java.warehouse.whCore.entity.User;

import org.apache.commons.lang3.Validate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ManagerDao extends JpaRepository<Manager,Long> {

	@Query("select m from Manager m where m.userName=?1")
	List<Manager> findUserByName(String name) throws Exception;
	
	@Query("select m from Manager m where m.warehouseId=?1")
	List<Manager> findUserByWarehouseId(String WarehouseId) throws Exception;
	
	
	@Modifying
	@Query(value = "DELETE FROM `manager` WHERE username=?1 and warehouseId=?2",nativeQuery=true)
	public void deleteManager(String userName, String warehouseId);
	
	@Modifying
	@Query(value = "DELETE FROM `manager` WHERE warehouseId=?1",nativeQuery=true)
	public void deleteManagerByWarehouseId(String warehouseId);
	
}
