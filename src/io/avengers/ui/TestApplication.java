package io.avengers.ui;

import io.avengers.service.HeroService;

public class TestApplication {
	
	public static void main(String[] args) throws Exception {
		HeroService service = new HeroService();
		System.out.println(service.findAll());
		
		
	}

}
