package org.springframework.samples.petclinic.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.service.VetService;


@ExtendWith(MockitoExtension.class)
class SpecialtyFormatterTests {
	
	@Mock
	private VetService vetService;
	
	private SpecialtyFormatter specialtyFormatter;
	
	@BeforeEach
	void setup() {
		specialtyFormatter = new SpecialtyFormatter(vetService);
	}
	
	@Test
	void testPrint() {
		final Specialty specialty = new Specialty();
		specialty.setName("anesthesiology");
		final String specialtyName = specialtyFormatter.print(specialty, Locale.ENGLISH);
		assertEquals("anesthesiology",specialtyName);
	}
	
	@Test
	void shouldParse() throws ParseException{
		Mockito.when(vetService.findSpecialties()).thenReturn(makeSpecialties());
		final Specialty specialty = specialtyFormatter.parse("dentistry", Locale.ENGLISH);
		assertEquals("dentistry",specialty.getName());	
	}
	
	@Test
	void shouldThrowParseException() throws ParseException{
		Mockito.when(vetService.findSpecialties()).thenReturn(makeSpecialties());
		Assertions.assertThrows(ParseException.class, () -> {
			specialtyFormatter.parse("anestesia", Locale.ENGLISH);
		});
	}

	private Collection<Specialty> makeSpecialties() {
		final Collection<Specialty> specialties = new ArrayList<>();
		specialties.add(new Specialty() {
			{
				setName("radiology");
			}
		});
		specialties.add(new Specialty() {
			{
				setName("surgery");
			}
		});
		specialties.add(new Specialty() {
			{
				setName("dentistry");
			}
		});
		
		return specialties;
	}

}
