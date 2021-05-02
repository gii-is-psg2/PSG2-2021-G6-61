package org.springframework.samples.petclinic.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Causa;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PropuestaAdopcion;
import org.springframework.samples.petclinic.repository.PropuestaAdopcionRepository;
import org.springframework.samples.petclinic.service.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropuestaAdopcionService extends BaseService<PropuestaAdopcion>{

	@Autowired
	private PropuestaAdopcionRepository repository;
	
	@Transactional
	public void deleteByPet(Pet pet) {
		this.repository.deleteByPet(pet);
	}
	
	@Transactional(readOnly = true)
	public List<PropuestaAdopcion> findByPet(Pet pet){
		return this.repository.findByPet(pet);
	}
	
	@Transactional(readOnly = true)
	public List<PropuestaAdopcion> findByOwner(Owner owner){
		return this.repository.findByOwner(owner);
	}
	
	@Transactional(readOnly = true)
	public PropuestaAdopcion findPropuestaOwnerPet(Owner owner,Pet pet){
		List<PropuestaAdopcion> propuestas = this.repository.findByOwner(owner);
		PropuestaAdopcion res =  null;
		
		for(PropuestaAdopcion propuesta: propuestas) { 
			if(propuesta.getPet().equals(pet)) res = propuesta; 
		}	
		
		return res;
	}
}
