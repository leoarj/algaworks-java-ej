package com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.CustomDispatcherResolvableItem;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.Resolvable;

public class CustomGatewayResolverImpl implements Resolvable<CustomDispatcherResolvableItem> {
	private long id;
	private String description;
	private List<Long> childStatusCodesList;
	private boolean initialized;
	
	public CustomGatewayResolverImpl() {
		// Defaults
		this.id = -1;
		this.description = "Is empty";
		this.childStatusCodesList = new ArrayList<>(10);
		initialized = false;
	}
	
	public CustomGatewayResolverImpl(long id, String description, int initialLengthChildStatusCodeList) {
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
	public Resolvable<CustomDispatcherResolvableItem> init() throws IllegalStateException {
		if (childStatusCodesList == null) {
			throw new IllegalStateException("Tamanho inicial da lista de códigos de status inválido");
		}
		System.out.println("Resolver id: " + id + " - " + description + " is started!");
		initialized = true;
		return this;
	}
	
	@Override
	public Resolvable<CustomDispatcherResolvableItem> resolve(CustomDispatcherResolvableItem item) throws IllegalStateException {
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
