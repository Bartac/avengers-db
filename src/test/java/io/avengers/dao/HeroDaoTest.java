package io.avengers.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.avengers.domain.Hero;
import io.avengers.domain.Movie;

public class HeroDaoTest {

	HeroDAO dao;
	Connection connect;
	
	@Before
	public void setUp() throws Exception {
		dao = new HeroDAO();
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
	public void testFindHeroesByName() throws SQLException {
		Set<Hero> heroes = dao.findHeroesByName("");
		
		//Check the size of findall request is the same of findbyname request with a void term
		assertTrue(dao.findAll().size() == heroes.size());
		
		//Check if a hero doesn't exist
		assertFalse(heroes.contains(dao.findHeroesByName("Patateman")));
		assertFalse(heroes.contains(dao.findHeroesByName("captain tomate")));
		assertFalse(heroes.contains(dao.findHeroesByName("Yolo")));
		
		//Check if a hero exist
		assertTrue(heroes.contains(new Hero("Ironman")));
		assertTrue(heroes.contains(new Hero("Captain America")));
		assertTrue(heroes.contains(new Hero("loki")));
	}

}
