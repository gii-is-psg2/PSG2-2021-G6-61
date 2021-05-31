package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "donaciones")
public class Donation extends BaseEntity{
	
	private String donante;
	
	@ManyToOne
	@JoinColumn(name = "causa_id")
	private Causa causa;
	
	
	@DateTimeFormat
	private LocalDate fecha;
	
	@Min(value=1, message="You have to donate at least 1 euro")
	@NotNull
	@Digits(integer = 12,fraction = 2, message = "Money just have two decimal numbers")
	private Double cantidad;

}
