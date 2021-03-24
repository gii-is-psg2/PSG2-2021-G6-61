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

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Book;
import org.springframework.samples.petclinic.service.BookService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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

	@Autowired
	public BookController(PetService petService, BookService bookService) {
		this.petService = petService;
		this.bookService = bookService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}


	@GetMapping(value = "/owners/*/pets/{petId}/books/new")
	public String initNewBookForm(@PathVariable("petId") int petId, Map<String, Object> model) {
		return "pets/createOrUpdateBookForm";
	}


	@PostMapping(value = "/owners/{ownerId}/pets/{petId}/books/new")
	public String processNewBookForm(@Valid Book book, BindingResult result) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateBookForm";
		}
		else {
			this.bookService.save(book);
			return "redirect:/owners/{ownerId}";
		}
	}

}
