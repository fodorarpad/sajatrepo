package hu.neuron.java.warehouse.whCore.dao;

import hu.neuron.java.warehouse.whCore.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface UserDao extends JpaRepository<User, Long> {

	@Query("select u from User u where u.userName=?1")
	User findUserByName(String name) throws Exception;

	@Query("select u from User u where u.email=?1")
	User findUserByEmail(String email) throws Exception;

	Page<User> findByUserNameStartsWith(String filter, Pageable pageable);

	Page<User> findByFullNameStartsWith(String filter, Pageable pageable);

	Page<User> findByEmailStartsWith(String filter, Pageable pageable);

	Page<User> findByPhoneNumberStartsWith(String filter, Pageable pageable);

	Page<User> findByUserNameStartsWithAndFullNameStartsWithAndEmailStartsWithAndPhoneNumberStartsWith(
			String filter1, String filter2, String filter3, String filter4, Pageable pageable);
}
