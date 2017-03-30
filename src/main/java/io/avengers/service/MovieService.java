package io.avengers.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

import io.avengers.dao.HeroDAO;
import io.avengers.dao.MovieDAO;
import io.avengers.domain.Movie;

public class MovieService {

	IllegalStateException stateException = new IllegalStateException("Error, contact your admin");

	public Set<Movie> findAll() {
		try {
			return new MovieDAO().findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw stateException;
		}
	}

	public Set<Movie> findMoviesByName(String term) {

		// Guards
		if (term == null) {
			System.out.println("Potential Bug or illegal requesta");
			return this.findAll();
		}
		if (term.isEmpty()) {
			return this.findAll();
		}

		// Method
		try {
			return new MovieDAO().findMoviesByName(term);
		} catch (SQLException e) {
			e.printStackTrace();
			throw stateException;
		}
	}
	
	public Movie findMoviesById(String term) {

		// Guards
		if (term == null) {
			System.out.println("Potential Bug or illegal requesta");
			return null;
		}
		if (term.isEmpty()) {
			return null;
		}

		// Method
		try {
			return new MovieDAO().findMoviesById(term);
		} catch (SQLException e) {
			e.printStackTrace();
			throw stateException;
		}
	}
	
	public int createMovie(String movie_name){
		//Guards
		if (movie_name == null){
			System.out.println("Potential Bug or illegal requesta");
			return -1;
		}
		if (movie_name.isEmpty()){
			System.out.println("Potential Bug or illegal requesta");
			return -1;
		}
		
		
		// Method
		try {
			return new MovieDAO().createMovie(movie_name);
		} catch (SQLException e) {
			e.printStackTrace();
			throw stateException;
		}
		
		
		
	}

	public boolean deleteMovie(int id) {
		if (id <= 0){
			System.out.println("Error, null or negative");
			return false;
		}
		try {
			new MovieDAO().deleteMovie(id);
			return true;
		} catch (SQLException e) {
			throw stateException;
		}
	}
}
