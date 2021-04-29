package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PropuestaAdopcion;
import org.springframework.stereotype.Repository;

@Repository
public interface PropuestaAdopcionRepository extends JpaRepository<PropuestaAdopcion, Integer>{
	
	void deleteByPet(Pet pet);
	
	List<PropuestaAdopcion> findByOwner(Owner owner);
	
	List<PropuestaAdopcion> findByPet(Pet pet);

}
