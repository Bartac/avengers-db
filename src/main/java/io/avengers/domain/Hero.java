package io.avengers.domain;

import java.util.ArrayList;
import java.util.List;

public class Hero {
	int id;
	String name;
	Sex sex;
	byte[] picture;
	String abilities;
	String history;
	List<String> movies_name = new ArrayList<>();
	String team_name;
	String real_name;

	public Hero(){
		
	}
	
	public Hero(int id, String name, Sex sex, byte[] picture, String abilities, String history, List<String> movies_name,
			String team_name, String real_name) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.picture = picture;
		this.abilities = abilities;
		this.history = history;
		this.movies_name.addAll(movies_name);
		this.team_name = team_name;
		this.real_name = real_name;
	}
	
	@Override
	public String toString() {
		return this.name +" - "+ this.movies_name;
	}

	public Hero(String name) {
		super();
		this.name = name;
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

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getAbilities() {
		return abilities;
	}

	public void setAbilities(String abilities) {
		this.abilities = abilities;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public List<String> getMovies_name() {
		return movies_name;
	}

	public void setMovies_name(List<String> movies_name) {
		this.movies_name = movies_name;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
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
		Hero other = (Hero) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
