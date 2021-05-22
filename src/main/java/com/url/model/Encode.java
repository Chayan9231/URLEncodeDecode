package com.url.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Encode {
	
	@NotNull
	private String encoded;
}