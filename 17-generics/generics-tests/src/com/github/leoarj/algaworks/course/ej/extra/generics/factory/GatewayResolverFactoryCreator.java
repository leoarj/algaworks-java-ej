package com.github.leoarj.algaworks.course.ej.extra.generics.factory;

import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.ResolvableItem;

public final class GatewayResolverFactoryCreator {
	public enum Implementation {
		DEFAULT,
		CUSTOM;
	}
	
	public static GatewayResolverFactory<? extends ResolvableItem> createGatewayResolverFactory(Implementation impl) {
		switch(impl) {
			case DEFAULT: {
				return new DefaultGatewayResolverFactory();
			}
			case CUSTOM: {
				return new CustomGatewayResolverFactory();
			}
			default: {
				return null;
			}
		}
	}
}
