package io.avengers.domain;

public class Team {
	int id;
	String team_name;
	byte[] team_picture;
	String history;
	String heroes_name;
	byte[] heroes_picture;
	
	public Team(String team_name) {
		this.team_name = team_name;
	}

	public Team(int id, String team_name, byte[] team_picture, String history, String heroes_name,
			byte[] heroes_picture) {
		this.id = id;
		this.team_name = team_name;
		this.team_picture = team_picture;
		this.history = history;
		this.heroes_name = heroes_name;
		this.heroes_picture = heroes_picture;
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

	public String getHeroes_name() {
		return heroes_name;
	}

	public void setHeroes_name(String heroes_name) {
		this.heroes_name = heroes_name;
	}

	public byte[] getHeroes_picture() {
		return heroes_picture;
	}

	public void setHeroes_picture(byte[] heroes_picture) {
		this.heroes_picture = heroes_picture;
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
