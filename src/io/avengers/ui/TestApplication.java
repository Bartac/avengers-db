package io.avengers.ui;

import io.avengers.service.HeroService;
import io.avengers.service.MovieService;


public class TestApplication {
	
	public static void main(String[] args) throws Exception {
		HeroService hService = new HeroService();
		MovieService mService = new MovieService();
		System.out.println(mService.findMoviesByName("aven"));
		System.out.println(hService.findHeroesByName("or"));
	}
}
