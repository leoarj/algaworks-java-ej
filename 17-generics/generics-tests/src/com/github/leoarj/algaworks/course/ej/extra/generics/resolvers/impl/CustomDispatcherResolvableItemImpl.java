package com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.impl;

import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.CustomDispatcherResolvableItem;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.ResolvableItem;
import java.util.Random;

public class CustomDispatcherResolvableItemImpl implements CustomDispatcherResolvableItem {
	private long internalStatus;
	
	private void internalResolveCalc() {
		internalStatus = Math.abs(new Random().nextLong());
		System.out.println("Custom Resolvable Item id: " + internalStatus + " is resolved.");
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
