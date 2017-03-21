package io.avengers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
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
				+ "FROM movie_hero mh RIGHT JOIN heroes h ON mh.id_hero=h.id "
				+ "RIGHT JOIN movie m ON m.id=mh.id_movie WHERE m.name LIKE '%%' ORDER BY m.id";

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

	public Movie findMoviesById(String term) throws SQLException {
		String query = "SELECT m.id, m.name, h.name AS hero_name, m.picture, m.history, m.date "
				+ "FROM movie_hero mh RIGHT JOIN heroes h ON mh.id_hero=h.id "
				+ "RIGHT JOIN movie m ON m.id=mh.id_movie WHERE m.id='" + term + "' ORDER BY m.id";

		Connection connect = connectToMySQL();
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		if (resultSet.next()) {
			Movie movie = resultSetToMovie(resultSet);
			connect.close();
			return movie;
		} else {
			connect.close();
			return null;
		}
	}

	Movie resultSetToMovie(ResultSet resultSet) {

		try {
			List<String> heroes_name = new ArrayList<>();

			// Get movies parameters from query
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			byte[] picture = resultSet.getBytes("picture");
			String history = resultSet.getString("history");
			Date date = resultSet.getDate("date");
			heroes_name.add(resultSet.getString("hero_name"));

			// If the next row have the same movie_id, add the next hero to the
			// list of heroes for this movie
			while (resultSet.next()) {
				if (id == resultSet.getInt("id")) {
					heroes_name.add(resultSet.getString("hero_name"));
				} else {
					// If the next row doesn't have the same movie_id, return
					// the movie and set the previous row of the query
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

	public int createMovie(String movie_name) throws SQLException {
		String query = "INSERT INTO `movie` (`name`) VALUES (?)";
		Connection connect = connectToMySQL();
		PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, movie_name);
		statement.execute();
		ResultSet rs = statement.getGeneratedKeys();
		int id = -1;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		connect.close();
		return id;
	}

	public void deleteMovie(int id) throws SQLException {
		Connection connect = connectToMySQL();
		
		String queryIrl = "DELETE FROM `movie_hero` WHERE id_movie=?";
		PreparedStatement staIrl = connect.prepareStatement(queryIrl, Statement.RETURN_GENERATED_KEYS);
		staIrl.setInt(1, id);
		staIrl.execute();
		
		String queryHero = "DELETE FROM `movie` WHERE id =?";
		PreparedStatement staHero = connect.prepareStatement(queryHero, Statement.RETURN_GENERATED_KEYS);
		staHero.setInt(1, id);
		staHero.execute();
		connect.close();
	}

}
