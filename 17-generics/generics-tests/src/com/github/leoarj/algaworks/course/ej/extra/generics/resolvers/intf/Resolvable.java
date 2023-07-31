package com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf;
public interface Resolvable<T extends ResolvableItem> {
	public long getId();
	public String getDescription();	
	public Resolvable<T> init() throws IllegalStateException;
	public Resolvable<T> resolve(T item) throws IllegalStateException;
}
