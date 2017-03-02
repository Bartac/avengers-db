package io.avengers.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.avengers.domain.Movie;

public class MovieDAO extends MarvelDAO {

	public MovieDAO() {
		super();
	}

	public Set<Movie> findAll() throws SQLException {

		String query = "SELECT m.id, m.name, h.name AS hero_name, m.picture, m.history, m.date "
				+ "FROM movie_hero mh JOIN heroes h ON mh.id_hero=h.id "
				+ "JOIN movie m ON m.id=mh.id_movie WHERE m.name LIKE '%%' ORDER BY m.id";

		Connection connect = connectToMySQL();

		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		Set<Movie> movies = new HashSet<>();

		while (resultSet.next()) {
			movies.add(resultSetToMovie(resultSet));
		}

		connect.close();
		return movies;
	}

	public Set<Movie> findMoviesByName(String term) throws SQLException {
		String query = "SELECT m.id, m.name, h.name AS hero_name, m.picture, m.history, m.date "
				+ "FROM movie_hero mh JOIN heroes h ON mh.id_hero=h.id "
				+ "JOIN movie m ON m.id=mh.id_movie WHERE m.name LIKE '%" + term + "%' ORDER BY m.id";

		Connection connect = connectToMySQL();
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		Set<Movie> movies = new HashSet<>();

		while (resultSet.next()) {
			movies.add(resultSetToMovie(resultSet));
		}

		connect.close();
		return movies;
	}

	Movie resultSetToMovie(ResultSet resultSet) {

		try {
			List<String> heroes_name = new ArrayList<>();

			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			byte[] picture = resultSet.getBytes("picture");
			String history = resultSet.getString("history");
			Date date = resultSet.getDate("date");
			heroes_name.add(resultSet.getString("hero_name"));

			while (resultSet.next()) {
				if (id == resultSet.getInt("id")) {
					heroes_name.add(resultSet.getString("hero_name"));
				} else {
					resultSet.previous();
					Movie m = new Movie(id, name, picture, history, date, heroes_name);
					return m;
				}
			}

			Movie m = new Movie(id, name, picture, history, date, heroes_name);
			return m;
		} catch (SQLException e) {
			throw new IllegalStateException("DataBase has move: " + e.getMessage());
		}
	}
}
