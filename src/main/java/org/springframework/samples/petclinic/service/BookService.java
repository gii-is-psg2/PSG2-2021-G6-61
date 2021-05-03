/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Book;
import org.springframework.samples.petclinic.repository.BookRepository;
import org.springframework.samples.petclinic.service.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class BookService extends BaseService<Book>{
	
	@Autowired
	private BookRepository bookRepository;

	
	@Transactional(readOnly = true)
	public Collection<Book> findBooksByPet(final Integer petId) {
		return bookRepository.findByPetId(petId);
	}
	
	@Transactional(readOnly = true)
	public Collection<Book> findByRoomIdAndCheckinBetween(final Integer roomId, final LocalDate checkIn, final LocalDate checkOut) {
		return bookRepository.findByRoomIdAndCheckinBetween(roomId, checkIn, checkOut);
	}
	
	@Transactional(readOnly = true)
	public Collection<Book> findByRoomIdAndCheckoutBetween(final Integer roomId, final LocalDate checkIn, final LocalDate checkOut) {
		return bookRepository.findByRoomIdAndCheckoutBetween(roomId, checkIn, checkOut);
	}
	
	@Transactional(readOnly = true)
	public Collection<Book> findByRoomIdAndCheckinBeforeAndCheckoutAfter(final Integer roomId, final LocalDate checkIn, final LocalDate checkOut) {
		return bookRepository.findByRoomIdAndCheckinBeforeAndCheckoutAfter(roomId, checkIn, checkOut);
	}
	
	@Transactional(readOnly = true)
	public Collection<Book> findByPetIdAndCheckinBetween(final Integer roomId, final LocalDate checkIn, final LocalDate checkOut) {
		return bookRepository.findByPetIdAndCheckinBetween(roomId, checkIn, checkOut);
	}
	
	@Transactional(readOnly = true)
	public Collection<Book> findByPetIdAndCheckoutBetween(final Integer roomId, final LocalDate checkIn, final LocalDate checkOut) {
		return bookRepository.findByPetIdAndCheckoutBetween(roomId, checkIn, checkOut);
	}
	
	@Transactional(readOnly = true)
	public Collection<Book> findByPetIdAndCheckinBeforeAndCheckoutAfter(final Integer roomId, final LocalDate checkIn, final LocalDate checkOut) {
		return bookRepository.findByPetIdAndCheckinBeforeAndCheckoutAfter(roomId, checkIn, checkOut);
	}

}
