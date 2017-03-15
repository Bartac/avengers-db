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
	
	public void createHero(String name,String realname){
		if(name == null || realname == null){
			System.out.println("name inex");
		}
		try {
			new HeroDAO().createHero(name,realname);
		} catch (SQLException e) {
			throw stateException;
		}
	}
}
