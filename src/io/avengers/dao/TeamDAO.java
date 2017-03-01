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

		String query = "SELECT t.team_id, t.team_name, t.history, t.picture AS team_picture, h.name AS hero_name, h.picture AS hero_picture "
				+ "FROM team t LEFT JOIN team_hero th ON t.team_id = th.team_id "
				+ "LEFT JOIN heroes h ON th.hero_id = h.id "
				+ "WHERE t.team_name LIKE '%%'";

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
		
		String query = "SELECT t.team_id, t.team_name, t.history, t.picture AS team_picture, h.name AS hero_name, h.picture AS hero_picture "
				+ "FROM team t LEFT JOIN team_hero th ON t.team_id = th.team_id "
				+ "LEFT JOIN heroes h ON th.hero_id = h.id "
				+ "WHERE t.team_name LIKE '%"+term+"%'";

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
			String team_name = resultSet.getString("team_name");
			String history = resultSet.getString("history");
			byte[] team_picture = resultSet.getBytes("team_picture");
			String heroes_name = resultSet.getString("hero_name");
			byte[] heroes_picture = resultSet.getBytes("hero_picture");
			Team t = new Team(id, team_name, team_picture, history, heroes_name, heroes_picture);
			return t;

		} catch (SQLException e) {
			throw new IllegalStateException("DataBase has move: " + e.getMessage());
		}
	}
}
