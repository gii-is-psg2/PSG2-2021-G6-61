package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends  JpaRepository<User, String>{

	User findByUsername(String username); 

}
