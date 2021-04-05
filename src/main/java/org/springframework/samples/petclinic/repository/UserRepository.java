package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.User;


public interface UserRepository extends  JpaRepository<User, String>{
	
}
