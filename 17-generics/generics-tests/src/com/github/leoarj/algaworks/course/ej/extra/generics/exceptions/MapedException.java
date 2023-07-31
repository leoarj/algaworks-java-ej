package com.github.leoarj.algaworks.course.ej.extra.generics.exceptions;

public class MapedException extends Exception {
	
	private static final long serialVersionUID = -1318162627739052454L;
	
	private int errorCode;
	
	public MapedException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
}