package com.github.leoarj.algaworks.course.ej.extra.generics.factory;

import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.impl.DefaultResolverImpl;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.Resolvable;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.ResolvableItem;

public class DefaultGatewayResolverFactory implements GatewayResolverFactory<ResolvableItem> {

	@Override
	public Resolvable<ResolvableItem> createResolvable() {
		return new DefaultResolverImpl();
	}
}
