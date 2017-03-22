package io.avengers.service;

import java.sql.SQLException;
import java.util.Set;

import io.avengers.dao.HeroDAO;
import io.avengers.dao.MovieDAO;
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
	
	public Team findTeamById(String term) {
		if (term == null) {
			System.out.println("Potential bug or illegal request ");
			return null;
		}
		if (term.isEmpty()) {
			return null;
		}
		try {
			return new TeamDAO().findTeamById(term);
		} catch (SQLException e) {
			throw stateException;
		}
		
	}
	
	public void createTeam(String team_name){
		if(team_name == null){
			System.out.println("name inex");
		}
		try {
			new TeamDAO().createTeam(team_name);
		} catch (SQLException e) {
			e.printStackTrace();
			throw stateException;
		}
	}

	public void deleteTeam(int id) {
		if (id <= 0){
			System.out.println("Error, null or negative");
			return;
		}
		try {
			new TeamDAO().deleteTeam(id);
		} catch (SQLException e) {
			throw stateException;
		}
		
	}


}
