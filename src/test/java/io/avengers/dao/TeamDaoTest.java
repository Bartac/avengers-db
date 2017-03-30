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

import io.avengers.domain.Team;

public class TeamDaoTest {

	TeamDAO dao;
	Connection connect;

	@Before
	public void setUp() throws Exception {
		dao = new TeamDAO();
		connect = dao.connectToMySQL();
	}

	@After
	public void tearDown() throws Exception {
		connect.close();
	}

	@Test
	public void testFindAll() throws SQLException {
		assertTrue(dao.findAll().size() > 1);
	}

	@Test
	public void testFindTeamByName() throws SQLException {
		// Check if movie name avengers exist
		assertTrue(dao.findTeamByName("gers").contains(new Team("Avengers")));

		// Check if movie doesn't exist
		Set<Team> team = dao.findTeamByName("");
		assertFalse(team.contains(new Team("Test")));

		// Check if movie exist
		assertTrue(team.contains(new Team("Avengers")));
		assertTrue(team.contains(new Team("Xmen")));
	}
	
	@Test
	public void testFindTeamById() throws SQLException{
		//Find Same Team Avengers 1
		assertTrue(dao.findTeamById("1").getTeam_name().equals("Avengers"));
		
		//If the id is a string, return null
		assertTrue(dao.findTeamById("hey") == null);
		
		//If the id doesn't exist, return null
		assertTrue(dao.findTeamById("10") == null);
		
	}

	@Test
	public void testCreateAndDeleteTeam() throws SQLException{
		//Create a Team Justice League
		String s = "Justice League";
		Integer id = dao.createTeam(s);
		
		//Check if the team exists
		assertTrue(dao.findTeamByName(s).iterator().next().getTeam_name().equals(s));
		
		//Delete Team
		dao.deleteTeam(id);
		
		//Check id deleted team exist
		assertTrue(dao.findTeamById(id.toString())==null);
		
	}
	

}
