package io.avengers.service;

import java.util.Set;

import io.avengers.dao.HeroDAO;
import io.avengers.domain.Hero;

public class HeroService {
	
	public Set<Hero> findAll() throws Exception{
		return new HeroDAO().findAll();
		
	}

}
