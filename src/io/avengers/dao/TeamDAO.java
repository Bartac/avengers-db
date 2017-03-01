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
		
		String query = "SELECT t.team_name, t.history, t.picture, h.name, h.picture FROM team t INNER JOIN team_hero th ON t.team_id = th.team_id " 
 + "INNER JOIN heroes h ON th.hero_id = h.id WHERE t.team_name LIKE '%"+term+"%'";

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
			String name = resultSet.getString("team_name");
			byte[] picture = resultSet.getBytes("picture");
			String history = resultSet.getString("history");
			Team t = new Team(id,name,picture,history);
			return t;

		} catch (SQLException e) {
			throw new IllegalStateException("DataBase has move: " + e.getMessage());
		}
	}
}
