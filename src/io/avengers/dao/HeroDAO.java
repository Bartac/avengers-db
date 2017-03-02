package io.avengers.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.avengers.domain.Hero;
import io.avengers.domain.Sex;

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
}
