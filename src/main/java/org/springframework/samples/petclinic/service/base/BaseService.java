package org.springframework.samples.petclinic.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseService<T> {
	
	/** Repositorio genérico */
	@Autowired
	protected JpaRepository<T, Integer> genericRepository;

	public Optional<T> findById(final Integer id) {
		return genericRepository.findById(id);
	}

	public List<T> findAll() {
		return genericRepository.findAll();
	}

	public T save(final T entity) {
		return genericRepository.save(entity);
	}

	public List<T> saveAll(final Iterable<T> entities) {
		return genericRepository.saveAll(entities);
	}
	
	public void delete(final T entity) {
		genericRepository.delete(entity);		
	}
	
	public void deleteById(final Integer id) {
		genericRepository.deleteById(id);
	}

	public void deleteByIdSiExiste(final Integer id) {
		if(genericRepository.existsById(id)) {
			genericRepository.deleteById(id);
		}
	}

	public void deleteAll(final List<T> entities) {
		genericRepository.deleteAll(entities);
	}


}
