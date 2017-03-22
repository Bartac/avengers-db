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

import io.avengers.domain.Hero;
import io.avengers.domain.Movie;
import io.avengers.domain.Sex;
import io.avengers.domain.Team;
import io.avengers.service.HeroService;
import io.avengers.service.MovieService;
import io.avengers.service.TeamService;

public class HeroDAO extends MarvelDAO {

	public HeroDAO() {
		super();
	}

	public Set<Hero> findAll() throws SQLException {

		String query = "SELECT h.id, h.name, h.sex, i.name AS real_name, m.name AS movies_name, t.team_name AS team_name, h.picture, h.abilities, h.history, t.picture "
				+ "FROM heroes h LEFT JOIN movie_hero mh ON h.id = mh.id_hero "
				+ "LEFT JOIN movie m ON mh.id_movie = m.id " + "LEFT JOIN team_hero th ON th.hero_id = h.id "
				+ "LEFT JOIN team t ON t.team_id = th.team_id " + "LEFT JOIN irl i ON i.hero_id = h.id "
				+ "WHERE h.name LIKE '%%' ORDER BY h.id";

		Connection connect = connectToMySQL();
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		Set<Hero> heroes = new HashSet<>();

		while (resultSet.next()) {
			heroes.add(resultSetToHero(resultSet));
		}

		connect.close();
		return heroes;
	}

	public Set<Hero> findHeroesByName(String term) throws SQLException {

		String query = "SELECT h.id, h.name, h.sex, i.name AS real_name, m.name AS movies_name, t.team_name AS team_name, h.picture, h.abilities, h.history, t.picture "
				+ "FROM heroes h LEFT JOIN movie_hero mh ON h.id = mh.id_hero "
				+ "LEFT JOIN movie m ON mh.id_movie = m.id " + "LEFT JOIN team_hero th ON th.hero_id = h.id "
				+ "LEFT JOIN team t ON t.team_id = th.team_id " + "LEFT JOIN irl i ON i.hero_id = h.id "
				+ "WHERE h.name LIKE '%" + term + "%' ORDER BY h.id";

		Connection connect = connectToMySQL();
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		Set<Hero> heroes = new HashSet<>();

		while (resultSet.next()) {
			heroes.add(resultSetToHero(resultSet));
		}

		connect.close();
		return heroes;
	}
	public Hero findHeroesById(String term) throws SQLException {

		String query = "SELECT h.id, h.name, h.sex, i.name AS real_name, m.name AS movies_name, t.team_name AS team_name, h.picture, h.abilities, h.history, t.picture "
				+ "FROM heroes h LEFT JOIN movie_hero mh ON h.id = mh.id_hero "
				+ "LEFT JOIN movie m ON mh.id_movie = m.id " + "LEFT JOIN team_hero th ON th.hero_id = h.id "
				+ "LEFT JOIN team t ON t.team_id = th.team_id " + "LEFT JOIN irl i ON i.hero_id = h.id "
				+ "WHERE h.id='"+term +"'";

		Connection connect = connectToMySQL();
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		if (resultSet.next()){
			Hero hero = resultSetToHero(resultSet);
			connect.close();
			return hero;
		}
		else{
			connect.close();
			return null;
		}


	}
	Hero resultSetToHero(ResultSet resultSet) {

		try {
			List<String> movies_name = new ArrayList<>();

			// Get heroes parameters from query
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String sSex = resultSet.getString("sex");
			byte[] picture = resultSet.getBytes("picture");
			String abilities = resultSet.getString("abilities");
			String history = resultSet.getString("history");
			movies_name.add(resultSet.getString("movies_name"));
			String team_name = resultSet.getString("team_name");
			String real_name = resultSet.getString("real_name");

			// If the next row have the same hero_id, add the next movie to the list of movies for this hero
			while (resultSet.next()) {
				if (id == resultSet.getInt("id")) {
					movies_name.add(resultSet.getString("movies_name"));
				} else {
					//If the next row doesn't have the same hero_id, return the hero and set the previous row
					resultSet.previous();
					Hero h = new Hero(id, name, Sex.O, picture, abilities, history, movies_name, team_name, real_name);
					return h;
				}
			}

			Hero h = new Hero(id, name, Sex.O, picture, abilities, history, movies_name, team_name, real_name);
			return h;

		} catch (SQLException e) {
			throw new IllegalStateException("DataBase has move: " + e.getMessage());
		}
	}
	
	public int createHero(String name, String realname) throws SQLException{
		
		String query = "INSERT INTO `heroes` (`name`, `sex`, `likes`, `dislikes`, `picture`, `abilities`, `history`) VALUES (?, '', '0', '0', NULL, NULL, NULL)";
		Connection connect = connectToMySQL();
		PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, name);
		statement.execute();
		ResultSet rs = statement.getGeneratedKeys();
		int id = -1;
		if(rs.next()){
			id = rs.getInt(1);
		}
		
		System.out.println("id " + id);
		
		String queryirl = "INSERT INTO `irl` (`hero_id`, `name`) VALUES (?,?)";
		PreparedStatement statementirl = connect.prepareStatement(queryirl, Statement.RETURN_GENERATED_KEYS);
		statementirl.setInt(1, id);
		statementirl.setString(2, realname);
		statementirl.execute();
		
		connect.close();
		return id;
		
	}
	
	public void addHeroToTeam(String team_name, String hero_name) throws SQLException{
		
		TeamService team = new TeamService();
		Team t = team.findTeamByName(team_name).iterator().next();
		int id_team = t.getId();
		System.out.println(id_team);
		
		HeroService hero = new HeroService();
		Hero h = hero.findHeroesByName(hero_name).iterator().next();
		int id_hero = h.getId();
		System.out.println(id_hero);
		
		String query = "INSERT INTO `team_hero` (`team_id`, `hero_id`) VALUES (?, ?)";
		Connection connect = connectToMySQL();
		PreparedStatement statementirl = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		statementirl.setInt(1, id_team);
		statementirl.setInt(2, id_hero);
		statementirl.execute();
		
		connect.close();
	}
	
	public void deleteHero(int id) throws SQLException{
		
		Connection connect = connectToMySQL();
		/*
		String queryTeam = "DELETE FROM `team_hero` WHERE id_hero=?";
		PreparedStatement staTeam = connect.prepareStatement(queryTeam, Statement.RETURN_GENERATED_KEYS);
		staTeam.setInt(1, id);
		staTeam.execute();
		
		String queryMovie = "DELETE FROM `movie_hero` WHERE hero_id=?";
		PreparedStatement staMovie = connect.prepareStatement(queryMovie, Statement.RETURN_GENERATED_KEYS);
		staMovie.setInt(1, id);
		staMovie.execute();
		*/
		String queryIrl = "DELETE FROM `irl` WHERE hero_id=?";
		PreparedStatement staIrl = connect.prepareStatement(queryIrl, Statement.RETURN_GENERATED_KEYS);
		staIrl.setInt(1, id);
		staIrl.execute();
		
		String queryHero = "DELETE FROM `heroes` WHERE id =?";
		PreparedStatement staHero = connect.prepareStatement(queryHero, Statement.RETURN_GENERATED_KEYS);
		staHero.setInt(1, id);
		staHero.execute();
		
		String queryTeam = "DELETE FROM `team_hero` WHERE hero_id=?";
		PreparedStatement staTeam = connect.prepareStatement(queryTeam, Statement.RETURN_GENERATED_KEYS);
		staTeam.setInt(1, id);
		staTeam.execute();
		
		String queryMovie = "DELETE FROM `movie_hero` WHERE id_hero=?";
		PreparedStatement staMovie = connect.prepareStatement(queryMovie, Statement.RETURN_GENERATED_KEYS);
		staMovie.setInt(1, id);
		staMovie.execute();
		
		connect.close();
	}
	
	public void addHeroToMovie(int id_movie,int id_hero) throws SQLException{
		
		String query = "INSERT INTO `movie_hero` (`id_movie`, `id_hero`) VALUES (?, ?)";
		Connection connect = connectToMySQL();
		PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, id_movie);
		statement.setInt(2, id_hero);
		statement.execute();
		
		
	}
}
