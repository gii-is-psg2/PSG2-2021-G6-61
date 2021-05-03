package org.springframework.samples.petclinic.component.base;

import java.time.LocalDate;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public abstract class AbstractValidator implements BaseValidator {
	
	protected static final String ERROR_CODE = "error";
	
	@Override
	public void validarCadenaVacia(final Errors errors, final String field, final String fieldName, final String message) {
		if(StringUtils.isEmpty(field)) {
			errors.rejectValue(fieldName, ERROR_CODE, message);
		}
	}

	@Override
	public <T> boolean validarCampoNulo(final Errors errors, final T field, final String fieldName, final String message) {
		boolean nulos = false;
		
		if(field == null) {
			nulos = true;
			errors.rejectValue(fieldName, ERROR_CODE, message);
		}
		return nulos;
	}

	@Override
	public void validarFechaPasada(final Errors errors, final LocalDate date, final LocalDate dateThreshold, final String fieldName,
			final String message) {
		if(date.isBefore(dateThreshold)) {
			errors.rejectValue(fieldName, ERROR_CODE, message);
		}
	}

	@Override
	public void validarFechaFutura(final Errors errors, final LocalDate date, final LocalDate dateThreshold, final String fieldName,
			final String message) {
		if(date.isAfter(dateThreshold)) {
			errors.rejectValue(fieldName, ERROR_CODE, message);
		}
	}

}
