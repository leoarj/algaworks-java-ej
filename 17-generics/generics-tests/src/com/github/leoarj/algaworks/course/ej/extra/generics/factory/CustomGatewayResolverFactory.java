package com.github.leoarj.algaworks.course.ej.extra.generics.factory;

import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.impl.CustomGatewayResolverImpl;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.CustomDispatcherResolvableItem;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.Resolvable;

public class CustomGatewayResolverFactory implements GatewayResolverFactory<CustomDispatcherResolvableItem> {

	@Override
	public Resolvable<CustomDispatcherResolvableItem> createResolvable() {
		return new CustomGatewayResolverImpl();
	}
}
