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
	
	@Test
	public void testFindHeroesById() throws SQLException {
		Hero heros = dao.findHeroesById("1");
		
		//Spiderman's id = 1 (BDD)
		//check if it's true
		assertTrue(heros.equals(dao.findHeroesByName("Spiderman").iterator().next()));
		
		//check if with an other hero it's false
		assertFalse(heros.equals(dao.findHeroesByName("Hulk").iterator().next()));
		
	}
	
	@Test
	public void testCreateHero() throws SQLException{
		Integer id = dao.createHero("toto", "jack");
		
		assertTrue(id == dao.findHeroesById(id.toString()).getId());
		
		dao.deleteHero(id);
		
		assertTrue(dao.findHeroesById(id.toString()) == null);
		
	}
	
	@Test
	public void testAddHeroToTeam() throws SQLException{
		Integer id = dao.createHero("toto", "jack");
		
		dao.addHeroToTeam("Avengers", "toto");
		
		assertTrue(dao.findHeroesByName("toto").iterator().next().getTeam_name().equals("Avengers"));
		assertFalse(dao.findHeroesByName("toto").iterator().next().getTeam_name().equals("Xmen"));
		
		dao.deleteHero(id);
	}
	
	@Test
	public void testAddHeroToMovie() throws SQLException{
		Integer id = dao.createHero("toto", "jack");
		
		dao.addHeroToMovie(1, id);
		
		assertTrue(dao.findHeroesByName("toto").iterator().next().getMovies_name().iterator().next().equals("Iron Man"));
		assertFalse(dao.findHeroesByName("toto").iterator().next().getMovies_name().iterator().next().equals("Captain"));
		
		dao.deleteHero(id);
	}
	
	@Test
	public void testUpdateHero() throws SQLException{
		Integer id = dao.createHero("toto", "jack");
		
		dao.updateHero(id, "tintin");
		
		assertTrue(dao.findHeroesById(id.toString()).getName().equals("tintin"));
		assertFalse(dao.findHeroesById(id.toString()).getName().equals("toto"));
		
		dao.deleteHero(id);
	}

}
