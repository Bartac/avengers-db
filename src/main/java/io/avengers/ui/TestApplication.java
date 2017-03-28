package io.avengers.ui;

import io.avengers.service.HeroService;
import io.avengers.service.MovieService;
import io.avengers.service.TeamService;

public class TestApplication {

	public static void main(String[] args) throws Exception {
		TeamService tService = new TeamService();
		System.out.println(tService.findAll());
		System.out.println(tService.findTeamByName("Avengers"));
		System.out.println(tService.findTeamById("2"));
		
		MovieService mService = new MovieService();
		System.out.println(mService.findAll());
		System.out.println(mService.findMoviesByName("The Avengers"));
		System.out.println(mService.findMoviesById("5"));
		
		HeroService hService = new HeroService();
		System.out.println(hService.findAll());
		System.out.println(hService.findHeroesByName("captain"));
		System.out.println(hService.findHeroesById("6"));
	}
}
