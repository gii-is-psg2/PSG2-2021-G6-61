package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void deleteByPet(final Pet pet) {
		this.repository.deleteByPet(pet);
	}
	
	@Transactional(readOnly = true)
	public List<PropuestaAdopcion> findByPet(final Pet pet){
		return this.repository.findByPet(pet);
	}
	
	@Transactional(readOnly = true)
	public List<PropuestaAdopcion> findByOwner(final Owner owner){
		return this.repository.findByOwner(owner);
	}
	
	@Transactional(readOnly = true)
	public PropuestaAdopcion findPropuestaOwnerPet(final Owner owner,final Pet pet){
		final List<PropuestaAdopcion> propuestas = this.repository.findByOwner(owner);
		PropuestaAdopcion res =  null;
		
		for(final PropuestaAdopcion propuesta: propuestas) { 
			if(propuesta.getPet().equals(pet)) res = propuesta; 
		}	
		
		return res;
	}
}
