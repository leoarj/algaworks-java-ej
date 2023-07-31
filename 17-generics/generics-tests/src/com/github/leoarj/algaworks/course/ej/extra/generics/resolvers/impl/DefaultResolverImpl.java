package com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.Resolvable;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.ResolvableItem;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.constants.ResolversDescriptions;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.constants.ResolversChildStatusCodeListSizeConstraints;

public class DefaultResolverImpl implements Resolvable<ResolvableItem> {

	private long id;
	private String description;
	private List<Long> childStatusCodesList;
	private boolean initialized;
	
	public DefaultResolverImpl() {
		// Defaults
		this.id = -1;
		this.description = ResolversDescriptions.DEFAULT_DESCRIPTION.getValue();
		this.childStatusCodesList = new ArrayList<>(ResolversChildStatusCodeListSizeConstraints.DEFAULT_INITIAL_LENGTH.getValue());
		initialized = false;
	}
	
	public DefaultResolverImpl(long id, String description, int initialLengthChildStatusCodeList) {
		this.id = id;
		this.description = description;
		this.childStatusCodesList = new ArrayList<>(initialLengthChildStatusCodeList);
		initialized = false;
	}
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public Resolvable<ResolvableItem> init() throws IllegalStateException {
		if (childStatusCodesList == null) {
			throw new IllegalStateException("Tamanho inicial da lista de códigos de status inválido");
		}
		System.out.println("Resolver id: " + id + " - " + description + " is started!");
		initialized = true;
		return this;
	}
	
	@Override
	public Resolvable<ResolvableItem> resolve(ResolvableItem item) throws IllegalStateException {
		if (!initialized) {
			throw new IllegalStateException("Not initialized");
		}
		if (item == null) {
			throw new IllegalStateException("Not suported");
		}
		childStatusCodesList.add(item.processInternalResolving().getInternalStatus());
		return this;
	}
}
