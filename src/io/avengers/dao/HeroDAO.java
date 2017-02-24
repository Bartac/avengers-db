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

		String query = "SELECT * FROM `heroes` WHERE name = '" + term + "'";

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
			Hero h = new Hero(id, name, Sex.O, picture, abilities, history);
			return h;

		} catch (SQLException e) {
			throw new IllegalStateException("DataBase has move: " + e.getMessage());
		}
	}
}
