package io.avengers.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import io.avengers.domain.Hero;
import io.avengers.domain.Sex;

public class HeroDAO {
	static Class c;

	public HeroDAO() {

		if (c == null) {
			try {
				c = Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException("SQLDriver is not here: " + e.getMessage());
			}
		}

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

		String query = "SELECT h.name, h.sex, i.name, m.name, t.team_name, h.picture, h.abilities, h.history, t.picture FROM heroes h INNER JOIN movie_hero mh ON h.id = mh.id_hero INNER JOIN movie m ON mh.id_movie = m.id INNER JOIN team_hero th ON th.hero_id = h.id INNER JOIN team t ON t.team_id = th.team_id INNER JOIN irl i ON i.hero_id = h.id WHERE h.name = '"
				+ term + "";

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
			long likes = resultSet.getLong("likes");
			long dislikes = resultSet.getLong("dislikes");
			Hero h = new Hero(id, name, Sex.O, likes, dislikes);
			return h;
		} catch (SQLException e) {
			throw new IllegalStateException("Database Broken: " + e.getMessage());
		}

	}

	public Connection connectToMySQL() {
		// 3306 no password
		Connection connect;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/marvel", "root", "");
			return connect;
		} catch (SQLException e) {
			throw new IllegalStateException("Wrong credentials or url, or overloaded connection: " + e.getMessage());
		}

	}
}
