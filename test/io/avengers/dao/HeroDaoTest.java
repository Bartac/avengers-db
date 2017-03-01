package io.avengers.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.avengers.domain.Hero;

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
	public void testHeroDAO() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() throws SQLException {
		assertTrue(dao.findAll().size() > 5);
	}

	@Test
	public void testFindHeroesByName() throws SQLException {
		//Set<Hero> hero = dao.findHeroesByName("Hulk");
		Hero hero = new Hero("Hulk");
		
		assertTrue(dao.findHeroesByName("ulk").contains(hero));
	}

	@Test
	public void testResultSetToHero() {
		fail("Not yet implemented");
	}

}
