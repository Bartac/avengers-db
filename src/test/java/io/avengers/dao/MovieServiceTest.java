package io.avengers.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.avengers.domain.Team;
import io.avengers.service.MovieService;
import io.avengers.service.TeamService;

public class MovieServiceTest {

	MovieService service;
	Connection connect;

	@Before
	public void setUp() throws Exception {
		service = new MovieService();
		connect = new MovieDAO().connectToMySQL();
	}

	@After
	public void tearDown() throws Exception {
		connect.close();
	}

	@Test
	public void testFindAll() throws SQLException {
		//Test if a team exists >1
		assertTrue(service.findAll().size() > 1);
	}
	
	@Test
	public void testFindMoviesbyName() throws SQLException {
		// Check if a request null or empty return a findallmethod
		assertTrue(service.findMoviesByName("").equals(service.findAll()));
		assertTrue(service.findMoviesByName(null).equals(service.findAll()));
		
	}
	@Test
	public void testFindMoviesById() throws SQLException{
		
		//If the id is a string, return null
		assertTrue(service.findMoviesById("hey") == null);
		
		//If the id doesn't exist, return null
		assertTrue(service.findMoviesById("100") == null);
		
		//If the id is null, return null;
		assertTrue(service.findMoviesById(null) == null);
		
		//If the id is empty, return null;
		assertTrue(service.findMoviesById("") == null);
		
	}
	
	@Test
	public void testCreateAndDeleteMovies() throws SQLException{		
		//Check if we create a team null
		assertTrue(service.createMovie(null) == -1);
		
		//Check if we create a team empty
		assertTrue(service.createMovie("") == -1);
		
		//Check if a deleted id is negative or 0
		assertTrue(service.deleteMovie(-1) == false);
		assertTrue(service.deleteMovie(0) == false);
	}
	

}
