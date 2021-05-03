package org.springframework.samples.petclinic.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.component.base.AbstractValidator;
import org.springframework.samples.petclinic.component.base.BaseValidator;
import org.springframework.samples.petclinic.model.Book;
import org.springframework.samples.petclinic.service.BookService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class BookValidator extends AbstractValidator implements BaseValidator {
	
	private static final String BOOK_CHECKIN = "checkin";
	
	private static final String BOOK_CHECKOUT = "checkout";

	@Autowired
	private BookService bookService;

	@Override
	public void validate(final Object target, final Errors errors) {
		final Book book = (Book) target;

		boolean camposNulos = false;

		camposNulos = validarCampoNulo(errors, book.getRoom(), "room", "Debe seleccionar una habitación") || camposNulos;
		camposNulos = validarCampoNulo(errors, book.getCheckin(), BOOK_CHECKIN, "Debe seleccionar una fecha de checkin") || camposNulos;
		camposNulos = validarCampoNulo(errors, book.getCheckout(), BOOK_CHECKOUT, "Debe seleccionar una fecha de checkout") || camposNulos;

		if (!camposNulos) {

			validarFechaPasada(errors, book.getCheckout(), book.getCheckin(), BOOK_CHECKOUT, "La fecha del CheckOut debe ser posterior o igual a la fecha del checkIn");

			if (!bookService.findByRoomIdAndCheckinBetween(book.getRoom().getId(), book.getCheckin(), book.getCheckout()).isEmpty()
					|| !bookService.findByRoomIdAndCheckoutBetween(book.getRoom().getId(), book.getCheckin(), book.getCheckout()).isEmpty()
					|| !bookService.findByRoomIdAndCheckinBeforeAndCheckoutAfter(book.getRoom().getId(), book.getCheckin(), book.getCheckout()).isEmpty()) {
				errors.rejectValue("room", ERROR_CODE, "Ya está esa habitación revervada para esas fechas");
			}

			if (!bookService.findByPetIdAndCheckinBetween(book.getPet().getId(), book.getCheckin(), book.getCheckout()).isEmpty()
					|| !bookService.findByPetIdAndCheckoutBetween(book.getPet().getId(), book.getCheckin(), book.getCheckout()).isEmpty()
					|| !bookService.findByPetIdAndCheckinBeforeAndCheckoutAfter(book.getPet().getId(), book.getCheckin(), book.getCheckout()).isEmpty()) {
				errors.rejectValue(BOOK_CHECKIN, ERROR_CODE, book.getPet().getName() + " ya tiene una reserva para esas fechas");
			}
		}
	}

	@Override
	public boolean supports(final Class<?> clazz) {
		return Book.class.isAssignableFrom(clazz);
	}
}
