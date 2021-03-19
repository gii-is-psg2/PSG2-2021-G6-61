package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Authorities;



public interface AuthoritiesRepository extends  JpaRepository<Authorities, String>{
	
}
