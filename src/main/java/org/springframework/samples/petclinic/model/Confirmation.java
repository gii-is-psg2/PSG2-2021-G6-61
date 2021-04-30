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

	public static <T> Confirmation<T> init(String texto, String url, String data, T entity) {
		return new Confirmation<T>(texto, url, data, entity);

	}

}
