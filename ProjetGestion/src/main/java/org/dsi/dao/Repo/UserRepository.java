package org.dsi.dao.Repo;

import java.util.List;

import org.dsi.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "select * from Users u where u.last_name like %:keyword% or u.first_name like %:keyword%", nativeQuery = true)
	List<User> findByKeyword(@Param("keyword") String keyword);
	
	
	
	@Query(value = "select * from Users u where u.email=:mail", nativeQuery = true)
	User findByEmail(@Param("mail") String mail);
	
//    @Query("SELECT u FROM User u WHERE u.email = ?1")
//    public User findByEmail(String email);
	// Page<User> findByNomContains(String kw , Pageable pageable);
//	
//	public List<Produit> findByDesignation(String designation);
//	@Query("select p from Produit p where p.designation like :x")
//	public Page<Produit> chercherProduits(@Param("x")String mc,Pageable pageable);
	
}
