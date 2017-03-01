package io.avengers.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.avengers.domain.Movie;

public class MovieDaoTest {

	MovieDAO dao;
	Connection connect;
	
	
	@Before
	public void setUp() throws Exception {
		dao = new MovieDAO();
		connect = dao.connectToMySQL();
	}

	@After
	public void tearDown() throws Exception {
		connect.close();
	}


	@Test
	public void testFindAll() throws SQLException {
		// Test if contains DB
		assertTrue(dao.findAll().size() > 5);
	}

	@Test
	public void testFindMoviesByName() throws Exception {
		// Check if movie name avengers exist
		assertTrue(dao.findMoviesByName("gers").contains(new Movie("The Avengers")));
		
		//Check if movie doesn't exist
		Set<Movie> movies = dao.findMoviesByName("");
		assertFalse(movies.contains(new Movie("Test")));
		
		//Check if movie exist
		assertTrue(movies.contains(new Movie("Thor")));
		assertTrue(movies.contains(new Movie("The Incredible Hulk")));
		assertTrue(movies.contains(new Movie("Captain America: Civil War")));
	}


}
