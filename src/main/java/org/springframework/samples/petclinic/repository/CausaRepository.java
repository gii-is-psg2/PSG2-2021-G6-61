package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Causa;
import org.springframework.stereotype.Repository;

@Repository("causaRepository")
public interface CausaRepository extends JpaRepository<Causa, Integer> {
	
	public List<Causa> findByAbiertaTrue();
	
}
