package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class Contact extends BaseEntity{

	private String friendlyname;
	
	private String email;
	
	private String phone;

}
