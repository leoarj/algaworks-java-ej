package com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.constants;

public enum ResolversDescriptions {

	DEFAULT_DESCRIPTION("Is empty");
	
	private String value;
	
	private ResolversDescriptions(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
