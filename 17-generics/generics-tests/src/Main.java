import java.util.ArrayList;
import java.util.List;

import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.constants.ResolversChildStatusCodeListSizeConstraints;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.impl.CustomDispatcherResolvableItemImpl;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.impl.CustomGatewayResolverImpl;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.impl.DefaultDispatcherResolvableItemImpl;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.impl.DefaultResolverImpl;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.CustomDispatcherResolvableItem;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.Resolvable;
import com.github.leoarj.algaworks.course.ej.extra.generics.resolvers.intf.ResolvableItem;

public class Main {
	public static void main(String[] args) {
		List<CustomDispatcherResolvableItem> customResolvableItemList = new ArrayList<>(
				ResolversChildStatusCodeListSizeConstraints
					.DEFAULT_INITIAL_LENGTH
					.getValue()
				);
		
		for (int i = 0; i < 10; i++) {
			customResolvableItemList.add(new CustomDispatcherResolvableItemImpl());
		}
		
		Resolvable<CustomDispatcherResolvableItem> resolverGateway =
				new CustomGatewayResolverImpl(1, "Resolver customizado", customResolvableItemList.size());
		
		resolverGateway
			.init();
		
		for (CustomDispatcherResolvableItem obj : customResolvableItemList) {
			resolverGateway
				.resolve(obj);
		}
		
		System.out.println("-------------");
		
		
		List<ResolvableItem> defaultResolvableItemList = new ArrayList<>(
				ResolversChildStatusCodeListSizeConstraints
					.DEFAULT_INITIAL_LENGTH
					.getValue()
				);
		
		for (int i = 0; i < 10; i++) {
			defaultResolvableItemList.add(new DefaultDispatcherResolvableItemImpl());
		}
				
		Resolvable<ResolvableItem> defaultResolverGateway = 
				new DefaultResolverImpl(2, "Resolver padrão", defaultResolvableItemList.size());
		
		defaultResolverGateway
			.init();
		
		for (ResolvableItem obj : defaultResolvableItemList) {
			defaultResolverGateway
				.resolve(obj);
		}
	}
}
