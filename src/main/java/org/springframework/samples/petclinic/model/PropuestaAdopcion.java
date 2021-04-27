package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "propuestas")
public class PropuestaAdopcion extends BaseEntity{
	
	@NotBlank
	@Length(min=0,max=500)
	String description;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "pet_id")
	Pet pet;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "owner_id")
	Owner owner;

}
