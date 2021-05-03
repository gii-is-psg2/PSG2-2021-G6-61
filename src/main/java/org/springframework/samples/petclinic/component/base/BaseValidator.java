package org.springframework.samples.petclinic.component.base;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public interface BaseValidator extends Validator {
	
	void validarCadenaVacia(Errors errors, String field, String fieldName, String message);
	
	<T> boolean validarCampoNulo(Errors errors, T field, String fieldName, String message);
	
	void validarFechaPasada(Errors errors, LocalDate date, LocalDate dateThreshold, String fieldName, String message);
	
	void validarFechaFutura(Errors errors, LocalDate date, LocalDate dateThreshold, String fieldName, String message);
	
}
