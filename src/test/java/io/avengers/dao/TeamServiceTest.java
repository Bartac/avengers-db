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
import io.avengers.service.TeamService;

public class TeamServiceTest {

	TeamService service;
	Connection connect;

	@Before
	public void setUp() throws Exception {
		service = new TeamService();
		connect = new TeamDAO().connectToMySQL();
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
	public void testFindTeambyName() throws SQLException {
		// Check if a request null or empty return a findallmethod
		assertTrue(service.findTeamByName("").equals(service.findAll()));
		assertTrue(service.findTeamByName(null).equals(service.findAll()));
		

		// Check if movie doesn't exist
		Set<Team> team = service.findTeamByName("");
		assertFalse(team.contains(new Team("Test")));

		// Check if movie exist
		assertTrue(team.contains(new Team("Avengers")));
		assertTrue(team.contains(new Team("Xmen")));
	}
	@Test
	public void testFindTeamById() throws SQLException{
		//Find Same Team Avengers 1
		assertTrue(service.findTeamById("1").getTeam_name().equals("Avengers"));
		
		//If the id is a string, return null
		assertTrue(service.findTeamById("hey") == null);
		
		//If the id doesn't exist, return null
		assertTrue(service.findTeamById("10") == null);
		
		//If the id is null, return null;
		assertTrue(service.findTeamById(null) == null);
		
		//If the id is empty, return null;
		assertTrue(service.findTeamById("") == null);
		
	}
	
	@Test
	public void testCreateAndDeleteTeam() throws SQLException{
		//Create a Team Justice League
		String s = "Justice League";
		Integer id = service.createTeam(s);
		
		//Check if the team exists
		assertTrue(service.findTeamByName(s).iterator().next().getTeam_name().equals(s));

		//Delete Team
		service.deleteTeam(id);	
		
		//Check id deleted team exist
		assertTrue(service.findTeamById(id.toString())==null);
		
		//Check if we create a team null
		assertTrue(service.createTeam(null) == 0);
		
		//Check if we create a team empty
		assertTrue(service.createTeam("") == 0);
		
		//Check if a deleted id is negative or 0
		assertTrue(service.deleteTeam(-1) == false);
		assertTrue(service.deleteTeam(0) == false);
	}
	
	}


