package io.avengers.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.avengers.dao.TeamDAO;

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
		assertTrue(dao.findAll().size() > 0);
	}

	@Test
	public void testFindTeamByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testResultSetToTeam() {
		fail("Not yet implemented");
	}

}
