package com.github.leoarj.algaworks.course.ej.extra.generics.factory;

import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.Resolvable;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.ResolvableItem;

public interface GatewayResolverFactory<T extends ResolvableItem> {
	public Resolvable<T> createResolvable();
}
