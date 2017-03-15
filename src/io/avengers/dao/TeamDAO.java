package io.avengers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.avengers.domain.Team;

public class TeamDAO extends MarvelDAO {

	public TeamDAO() {
		super();
	}

	public Set<Team> findAll() throws SQLException {

		String query = "SELECT t.team_id, t.team_name, t.history, t.picture AS team_picture, h.name AS hero_name, h.picture AS hero_picture "
				+ "FROM team t LEFT JOIN team_hero th ON t.team_id = th.team_id "
				+ "LEFT JOIN heroes h ON th.hero_id = h.id " + "WHERE t.team_name LIKE '%%'";

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
				+ "LEFT JOIN heroes h ON th.hero_id = h.id " + "WHERE t.team_name LIKE '%" + term + "%'";

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
	
	public Team findTeamById(String term) throws SQLException {

		String query = "SELECT t.team_id, t.team_name, t.history, t.picture AS team_picture, h.name AS hero_name, h.picture AS hero_picture "
				+ "FROM team t LEFT JOIN team_hero th ON t.team_id = th.team_id "
				+ "LEFT JOIN heroes h ON th.hero_id = h.id " + "WHERE t.team_id='"+ term + "'";

		Connection connect = connectToMySQL();
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		resultSet.next();
		Team team = resultSetToTeam(resultSet);
		connect.close();
		return team;
	}
	
	
	Team resultSetToTeam(ResultSet resultSet) {

		try {
			List<String> heroes_name = new ArrayList<>();
			List<byte[]> heroes_picture = new ArrayList<>();

			// Get team parameters from query
			int id = resultSet.getInt("team_id");
			String team_name = resultSet.getString("team_name");
			String history = resultSet.getString("history");
			byte[] team_picture = resultSet.getBytes("team_picture");
			heroes_name.add(resultSet.getString("hero_name"));
			heroes_picture.add(resultSet.getBytes("hero_picture"));

			// If the next row have the same team_id, add the next hero and
			// picture to the
			// list of heroes and picture for this team
			while (resultSet.next()) {
				if (id == resultSet.getInt("team_id")) {
					heroes_name.add(resultSet.getString("hero_name"));
					heroes_picture.add(resultSet.getBytes("hero_picture"));
				} else {
					// If the next row doesn't have the same team_id, return
					// the team and set the previous row of the query
					Team t = new Team(id, team_name, team_picture, history, heroes_name, heroes_picture);
					resultSet.previous();
					return t;
				}
			}

			Team t = new Team(id, team_name, team_picture, history, heroes_name, heroes_picture);
			return t;

		} catch (SQLException e) {
			throw new IllegalStateException("DataBase has move: " + e.getMessage());
		}
	}
	
	public void createTeam(String team_name) throws SQLException{
		
		String query = "INSERT INTO `team` (`team_id`, `team_name`, `picture`, `history`) VALUES (NULL,?, NULL, NULL)";
		Connection connect = connectToMySQL();
		PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, team_name);
		statement.execute();
		
		ResultSet rs = statement.getGeneratedKeys();
		int id = -1;
		if(rs.next()){
			id = rs.getInt(1);
		}
		
		System.out.println("id " + id);
		connect.close();
	}
}
