package io.avengers.service;

import java.sql.SQLException;
import java.util.Set;

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
}
