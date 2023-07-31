package com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.impl;

import java.util.Random;

import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.ResolvableItem;

public class DefaultDispatcherResolvableItemImpl implements ResolvableItem {

private long internalStatus;
	
	private void internalResolveCalc() {
		internalStatus = Math.abs(new Random().nextLong());
		System.out.println("Default Resolvable Item id: " + internalStatus + " is resolved.");
	}	
	
	@Override
	public long getInternalStatus() {
		return internalStatus;
	}
	
	@Override
	public ResolvableItem processInternalResolving() {
		internalResolveCalc();
		return this;
	}
}
