package io.avengers.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import io.avengers.domain.Team;

public class TeamDAO extends MarvelDAO{
	
	public TeamDAO() {
		super();
	}
	
	public Set<Team> findAll() throws SQLException {

		String query = "SELECT * FROM `team`";

		Connection connect = connectToMySQL();
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		Set<Team> team = new HashSet<>();

		while (resultSet.next()) {
			team.add(resultSetToTeam(resultSet));
		}

		connect.close();
		return team;


	}
	
	public Set<Team> findTeamByName(String term) throws SQLException {
		
		String query = "SELECT team_id,name,picture,history FROM `team` WHERE name = '"+term+"'";

		Connection connect = connectToMySQL();
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		Set<Team> team = new HashSet<>();

		while (resultSet.next()) {
			team.add(resultSetToTeam(resultSet));
		}
		
		connect.close();
		return team;
	}
	
	
	Team resultSetToTeam(ResultSet resultSet) {

		try {
			int id = resultSet.getInt("team_id");
			String name = resultSet.getString("name");
			byte[] picture = resultSet.getBytes("picture");
			String history = resultSet.getString("history");
			Team t = new Team(id,name,picture,history);
			return t;

		} catch (SQLException e) {
			throw new IllegalStateException("DataBase has move: " + e.getMessage());
		}
	}
}
