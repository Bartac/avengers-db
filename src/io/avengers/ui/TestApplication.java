package io.avengers.ui;

import io.avengers.service.HeroService;
import io.avengers.service.MovieService;
import io.avengers.service.TeamService;

public class TestApplication {

	public static void main(String[] args) throws Exception {
		TeamService tService = new TeamService();
		System.out.println(tService.findAll());
		
		MovieService mService = new MovieService();
		System.out.println(mService.findAll());
		
		HeroService hService = new HeroService();
		System.out.println(hService.findAll());
	}
}
