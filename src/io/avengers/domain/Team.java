package io.avengers.domain;

import java.util.ArrayList;
import java.util.List;

public class Team {
	int id;
	String team_name;
	byte[] team_picture;
	String history;
	List<String> heroes_name = new ArrayList<>();
	List<byte[]> heroes_picture = new ArrayList<>();
	
	
	public Team(){
		
	}
	
	public Team(String team_name) {
		this.team_name = team_name;
	}

	public Team(int id, String team_name, byte[] team_picture, String history, 	List<String> heroes_name,
			List<byte[]> heroes_picture) {
		this.id = id;
		this.team_name = team_name;
		this.team_picture = team_picture;
		this.history = history;
		this.heroes_name.addAll(heroes_name);
		this.heroes_picture.addAll(heroes_picture);
	}
	
	@Override
	public String toString() {
		return this.team_name +" : "+ this.heroes_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public byte[] getTeam_picture() {
		return team_picture;
	}

	public void setTeam_picture(byte[] team_picture) {
		this.team_picture = team_picture;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	
	
	public List<byte[]> getHeroes_picture() {
		return heroes_picture;
	}

	public void setHeroes_picture(List<byte[]> heroes_picture) {
		this.heroes_picture = heroes_picture;
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
		result = prime * result + ((team_name == null) ? 0 : team_name.hashCode());
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
		Team other = (Team) obj;
		if (team_name == null) {
			if (other.team_name != null)
				return false;
		} else if (!team_name.equals(other.team_name))
			return false;
		return true;
	}




}
