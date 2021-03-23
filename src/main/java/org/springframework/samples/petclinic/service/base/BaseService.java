package org.springframework.samples.petclinic.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class BaseService<T> {
	
	/** Repositorio genérico */
	@Autowired
	protected JpaRepository<T, Integer> genericRepository;

	public Optional<T> findById(final Integer id) {
		final Optional<T> returnEntity = genericRepository.findById(id);
		return returnEntity;
	}

	public List<T> findAll() {
		final List<T> returnEntities = genericRepository.findAll();
		return returnEntities;
	}

	public T save(final T entity) {
		final T returnEntity = genericRepository.save(entity);
		return returnEntity;
	}

	public List<T> saveAll(final Iterable<T> entities) {
		final List<T> returnEntity = genericRepository.saveAll(entities);
		return returnEntity;
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
