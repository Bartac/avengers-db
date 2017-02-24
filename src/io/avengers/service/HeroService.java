package io.avengers.service;

import java.sql.SQLException;
import java.util.Set;

import io.avengers.dao.HeroDAO;
import io.avengers.domain.Hero;

public class HeroService {
	
	IllegalStateException stateException = new IllegalStateException("BDD OFF: ");
	
	public Set<Hero> findAll(){
		try {
			return new HeroDAO().findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw stateException;
		}
	}
	
	public Set<Hero> findHeroesByName(String term){
		if (term == null){
			System.out.println("Potential bug or illegal request ");
			return this.findAll();
		}
		if (term.isEmpty()){
			return this.findAll();
		}
		try {
			return new HeroDAO().findHeroesByName(term);
		} catch (SQLException e) {
			throw stateException;
		}
	}
}
