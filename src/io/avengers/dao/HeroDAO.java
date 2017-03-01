package io.avengers.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import io.avengers.domain.Hero;
import io.avengers.domain.Sex;

public class HeroDAO extends MarvelDAO {

	public HeroDAO() {
		super();
	}

	public Set<Hero> findAll() throws SQLException {

		String query = "SELECT * FROM `heroes`";

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

		String query = "SELECT h.id, h.name, h.sex, i.name, m.name AS movies_name, t.team_name AS team_name, h.picture, h.abilities, h.history, t.picture FROM heroes h INNER JOIN movie_hero mh ON h.id = mh.id_hero " 
				+ "INNER JOIN movie m ON mh.id_movie = m.id INNER JOIN team_hero th ON th.hero_id = h.id INNER JOIN team t ON t.team_id = th.team_id INNER JOIN irl i ON i.hero_id = h.id "
				+ "WHERE h.name LIKE '%" + term +"%'";

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
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String sSex = resultSet.getString("sex");
			byte[] picture = resultSet.getBytes("picture");
			String abilities = resultSet.getString("abilities");
			String history = resultSet.getString("history");
			String movies_name = resultSet.getString("movies_name");
			String team_name = resultSet.getString("team_name");

			Hero h = new Hero(id, name, Sex.O, picture, abilities, history, movies_name, team_name);
			return h;

		} catch (SQLException e) {
			throw new IllegalStateException("DataBase has move: " + e.getMessage());
		}
	}
}
