package io.avengers.service;

import java.sql.SQLException;
import java.util.Set;

import io.avengers.dao.HeroDAO;
import io.avengers.domain.Hero;

public class HeroService {

	IllegalStateException stateException = new IllegalStateException("BDD OFF: ");

	public Set<Hero> findAll() {
		try {
			return new HeroDAO().findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw stateException;
		}
	}

	public Set<Hero> findHeroesByName(String term) {
		if (term == null) {
			System.out.println("Potential bug or illegal request ");
			return this.findAll();
		}
		if (term.isEmpty()) {
			return this.findAll();
		}
		try {
			return new HeroDAO().findHeroesByName(term);
		} catch (SQLException e) {
			throw stateException;
		}
	}
	public Hero findHeroesById(String term) {

		if (term == null) {
			System.out.println("Potential bug or illegal request ");
			return null;
		}
		if (term.isEmpty()) {
			return null;
		}
		try {
			return new HeroDAO().findHeroesById(term);
		} catch (SQLException e) {
			throw stateException;
		}
	}
	
	public int createHero(String name,String realname){
		if(name == null || realname == null){
			System.out.println("name inex");
			return -1;
		}
		try {
			return new HeroDAO().createHero(name,realname);
		} catch (SQLException e) {
			throw stateException;
		}
	}

	public void deleteHero(int id) {
		if (id <= 0){
			System.out.println("Error, null or negative");
			return;
		}
		try {
			new HeroDAO().deleteHero(id);
		} catch (SQLException e) {
			throw stateException;
		}
		
	}
	
	public void addHeroToTeam(String team_name, String hero_name){
		if(hero_name == null || team_name == null){
			System.out.println("name or team inex");
		}
		try {
			new HeroDAO().addHeroToTeam(team_name,hero_name);
		} catch (SQLException e) {
			throw stateException;
		}
	}
	
	public void addHeroToMovie(int id_movie, int id_hero){
		try {
			new HeroDAO().addHeroToMovie(id_movie,id_hero);
		} catch (SQLException e) {
			throw stateException;
		}
	}
	
	public void updateHero(int id_hero, String newname){
		try {
			new HeroDAO().updateHero(id_hero, newname);
		} catch (SQLException e) {
			throw stateException;
		}
	}
}
