package io.avengers.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.avengers.domain.Hero;
import io.avengers.domain.Team;
import io.avengers.service.HeroService;
import io.avengers.service.TeamService;

public class HeroServiceTest {

	HeroService service;
	Connection connect;

	@Before
	public void setUp() throws Exception {
		service = new HeroService();
		connect = new HeroDAO().connectToMySQL();
	}

	@After
	public void tearDown() throws Exception {
		connect.close();
	}
	
	@Test 
	public void testFindAll(){
		//Test if a Hero exists >1
		assertTrue(service.findAll().size() > 1);
	}
	
	@Test
	public void testFindHeroByName(){
		// Check if a request null or empty return a findallmethod
		assertTrue(service.findHeroesByName("").equals(service.findAll()));
		assertTrue(service.findHeroesByName(null).equals(service.findAll()));
	}
	
	@Test
	public void testCreateAndDeleteHero(){
		
		//Check if we create a team empty		
		TeamService ts = new TeamService();
		
		Team t = ts.findTeamById("1");
		
		Hero jo = service.createHero("Jo", "John", t);
		
		assertEquals(jo.getName(), "Jo");
		
		service.deleteHero(jo.getId());
	}
}
