package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "propuestas")
public class PropuestaAdopcion extends BaseEntity{
	
	@NotBlank
	@Length(min=0,max=500)
	String description;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	Pet pet;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	Owner owner;

}
