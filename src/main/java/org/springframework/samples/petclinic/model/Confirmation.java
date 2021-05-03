package org.springframework.samples.petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Confirmation<T> {

	private String texto;

	private String url;

	private String data;

	private T entity;

	public static <T> Confirmation<T> init(final String texto, final String url, final String data, final T entity) {
		return new Confirmation<>(texto, url, data, entity);

	}

}
