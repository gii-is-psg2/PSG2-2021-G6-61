package org.springframework.samples.petclinic.component;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Book;
import org.springframework.samples.petclinic.service.BookService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class BookValidator implements Validator{
	
	private static final Log LOG = LogFactory.getLog(BookValidator.class);

	@Autowired
	private BookService bookService;
	
	@Override
	public void validate(Object target, Errors errors) {
		Book book = (Book) target;
		
				if(book.getRoom()==null) {
					errors.rejectValue("room", "error","Debe seleccionar una habitación");
				}else if(book.getCheckin()==null) {
					errors.rejectValue("checkin", "error","Debe seleccionar una fecha de checkin");
				}else if(book.getCheckout()==null) {
					errors.rejectValue("checkout", "error","Debe seleccionar una fecha de checkout");
				}else {
				if(book.getCheckin().isBefore(LocalDate.now())) {
					errors.rejectValue("checkin", "error","La fecha del CheckIn debe ser posterior o igual al día de hoy");
				}
				
				if(book.getCheckout().isBefore(LocalDate.now())) {
					errors.rejectValue("checkout", "error","La fecha del CheckIn debe ser posterior o igual al día de hoy");
				}
				
				if(book.getCheckout()==null ||book.getCheckout().isBefore(book.getCheckin())) {
					errors.rejectValue("checkout", "error","La fecha del CheckOut debe ser posterior o igual a la fecha del checkIn");
				}
				
				
				if(bookService.findByRoomIdAndCheckinBetween(book.getRoom().getId(), book.getCheckin(), book.getCheckout()).size()>0) {
					errors.rejectValue("room", "error","Ya está esa habitación revervada para esas fechas");
				}else if(bookService.findByRoomIdAndCheckoutBetween(book.getRoom().getId(), book.getCheckin(), book.getCheckout()).size()>0) {
					errors.rejectValue("room", "error","Ya está esa habitación revervada para esas fechas");
				}else if(bookService.findByRoomIdAndCheckinBeforeAndCheckoutAfter(book.getRoom().getId(), book.getCheckin(), book.getCheckout()).size()>0) {
					errors.rejectValue("room", "error","Ya está esa habitación revervada para esas fechas");
				}
				
				if(bookService.findByPetIdAndCheckinBetween(book.getPet().getId(), book.getCheckin(), book.getCheckout()).size()>0) {
					errors.rejectValue("checkin", "error", book.getPet().getName()+" ya tiene una reserva para esas fechas");
				}else if(bookService.findByPetIdAndCheckoutBetween(book.getPet().getId(), book.getCheckin(), book.getCheckout()).size()>0) {
					errors.rejectValue("checkin", "error", book.getPet().getName()+" ya tiene una reserva para esas fechas");
				}else if(bookService.findByPetIdAndCheckinBeforeAndCheckoutAfter(book.getPet().getId(), book.getCheckin(), book.getCheckout()).size()>0) {
					errors.rejectValue("checkin", "error", book.getPet().getName()+" ya tiene una reserva para esas fechas");
				}
				}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Book.class.isAssignableFrom(clazz);
	}
}
