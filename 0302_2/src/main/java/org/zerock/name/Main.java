package org.zerock.name;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.zerock.config.RootConfig;
import org.zerock.service.Chef;
import org.zerock.service.Restaurant;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfig.class);

		System.out.println(ctx);

		Chef chef = ctx.getBean(Chef.class);

		System.out.println(chef);

		Restaurant restaurant = ctx.getBean(Restaurant.class);

		System.out.println(restaurant);
	}

}
