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
package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Simple JavaBean domain object representing an owner.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "causas", uniqueConstraints = @UniqueConstraint(columnNames = { "nombre" }))
public class Causa extends BaseEntity {

	@Column(name = "nombre")
	@NotBlank
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "objetivo_presupuestario")
	@NotNull
	@Min(0)
	private Double objetivoPresupuestario;
	
	@Column(name = "organizacion")
	@NotBlank
	private String organizacion;
	
	@Column(name = "abierta", columnDefinition = "integer default 1")
	private Boolean abierta;
	
}
