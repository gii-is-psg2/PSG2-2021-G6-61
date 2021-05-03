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
package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.component.BookValidator;
import org.springframework.samples.petclinic.model.Book;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Room;
import org.springframework.samples.petclinic.service.BookService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.RoomService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class BookController {

	private final PetService petService;
	private final BookService bookService;
	private final BookValidator bookValidator;
	private final RoomService roomService;

	@Autowired
	public BookController(final PetService petService, final BookService bookService, final BookValidator bookValidator,
			final RoomService roomService) {
		this.roomService = roomService;
		this.bookValidator = bookValidator;
		this.petService = petService;
		this.bookService = bookService;
	}

	@ModelAttribute("rooms")
	public Collection<Integer> populatePetTypes() {
		return this.roomService.findAll().stream().map(Room::getId).collect(Collectors.toList());
	}

	@ModelAttribute("book")
	public Book loadPetWithBook(@PathVariable("petId") final int petId) {
		final Pet pet = this.petService.findPetById(petId);
		final Book book = new Book();
		pet.addBook(book);
		return book;
	}

	@GetMapping(value = "/owners/*/pets/{petId}/books/new")
	public String initNewBookForm(@PathVariable("petId") final int petId, final Map<String, Object> model) {
		return "pets/createOrUpdateBookForm";
	}

	@PostMapping(value = "/owners/{ownerId}/pets/{petId}/books/new")
	public String processNewBookForm(@Valid final Book book, final BindingResult result) {

		ValidationUtils.invokeValidator(bookValidator, book, result);
		if (result.hasErrors()) {
			return "pets/createOrUpdateBookForm";
		} else {
			this.bookService.save(book);
			return "redirect:/owners/{ownerId}";
		}
	}

	@GetMapping(value = "/owners/{ownerId}/pets/{petId}/books/{bookId}/delete")
	public String processDeleteBook(@PathVariable("bookId") final int bookId)
			throws DataAccessException, DuplicatedPetNameException {
		final Optional<Book> book = bookService.findById(bookId);
		if (book.isPresent()) {
			final Book reserva = book.get();
			final Set<Book> books = new HashSet<>(reserva.getPet().getBooks().stream().collect(Collectors.toSet()));
			books.remove(reserva);
			final Pet pet = reserva.getPet();
			pet.setBooks(books);
			this.petService.savePet(pet);
			this.bookService.deleteById(bookId);
		}
		return "redirect:/owners/{ownerId}";
	}

}
