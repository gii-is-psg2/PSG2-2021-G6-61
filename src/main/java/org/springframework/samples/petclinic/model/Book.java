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

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
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
@Table(name = "books")
public class Book extends BaseEntity {

	@Column(name = "details")
	private String details;

	@Column(name = "checkin")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@FutureOrPresent
	@NotNull
	private LocalDate checkin;
	
	@Column(name = "checkout")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@FutureOrPresent
	@NotNull
	private LocalDate checkout;


	@ManyToOne
	@JoinColumn(name = "room_id")
	@NotNull
	private Room room;
	
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

}
