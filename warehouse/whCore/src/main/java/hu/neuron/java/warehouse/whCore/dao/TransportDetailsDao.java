package hu.neuron.java.warehouse.whCore.dao;

import hu.neuron.java.warehouse.whCore.entity.Transport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface TransportDetailsDao extends JpaRepository<Transport, Long> {

	@Modifying
	@Query(value = "INSERT INTO transport_details (ware_Id, piece, transportId)"
			+ "VALUES (?1, ?2, ?3)", nativeQuery = true)
	void addToTransportDetails(Long wareId, int piece, Long transportId)
			throws Exception;

	@Modifying
	@Query(value = "SELECT transportId from transport_details", nativeQuery = true)
	List<Long> findAllTransportid() throws Exception;

}
