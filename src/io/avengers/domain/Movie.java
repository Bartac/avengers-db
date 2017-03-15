package io.avengers.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie {
	int id;
	String name;
	byte[] picture;
	String history;
	Date date;
	List<String> heroes_name = new ArrayList<>();
	
	public Movie() {
		
	}

	public Movie(int id, String name, byte[] picture, String history, Date date, List<String> heroes_name) {
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.history = history;
		this.date = date;
		this.heroes_name.addAll(heroes_name);
	}

	public Movie(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name + " - " + this.heroes_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getHeroes_name() {
		return heroes_name;
	}

	public void setHeroes_name(List<String> heroes_name) {
		this.heroes_name = heroes_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
