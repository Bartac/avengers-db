package io.avengers.service;

import java.sql.SQLException;
import java.util.Set;

import io.avengers.dao.TeamDAO;
import io.avengers.domain.Team;

public class TeamService {

	IllegalStateException stateException = new IllegalStateException("BDD OFF: ");

	public Set<Team> findAll() {
		try {
			return new TeamDAO().findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw stateException;
		}
	}

	public Set<Team> findTeamByName(String term) {
		if (term == null) {
			System.out.println("Potential bug or illegal request ");
			return this.findAll();
		}
		if (term.isEmpty()) {
			return this.findAll();
		}
		try {
			return new TeamDAO().findTeamByName(term);
		} catch (SQLException e) {
			throw stateException;
		}
		
	}

}
