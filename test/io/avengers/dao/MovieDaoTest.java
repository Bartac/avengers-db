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
		// Check the numbers of movies
		assertTrue(dao.findAll().size() > 5);
	}

	@Test
	public void testFindMoviesByName() throws Exception {
		// Check if movie name avengers exist
		Set<Movie> movies1 = dao.findMoviesByName("gers");
		Movie m1 = new Movie("The Avengers");
		assertTrue(movies1.contains(m1));
		
		//Check if movie doesn't exist
		Set<Movie> movies2 = dao.findMoviesByName("");
		Movie m2 = new Movie("Test");
		assertFalse(movies2.contains(m2));
	}


}
