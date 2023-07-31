package com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.constants;

public enum ResolversChildStatusCodeListSizeConstraints {

	DEFAULT_INITIAL_LENGTH(10);
	
	private int value;
	
	private ResolversChildStatusCodeListSizeConstraints(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}
