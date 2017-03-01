package io.avengers.domain;

public class Hero {
	int id;
	String name;
	Sex sex;
	byte[] picture;
	String abilities;
	String history;
	String movies_name;
	String team_name;

	public Hero(int id, String name, Sex sex, byte[] picture, String abilities, String history, String movies_name, String team_name) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.picture = picture;
		this.abilities = abilities;
		this.history = history;
		this.movies_name = movies_name;
		this.team_name = team_name;
	}
	
	

	public Hero(String name) {
		super();
		this.name = name;
	}



	@Override
	public String toString() {
		return this.id + " - " + this.name;
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
	
	public String getMoviesName() {
		return movies_name;
	}

	public void setMoviesName(String movies_name) {
		this.movies_name = movies_name;
	}
	
	public String getTeamName() {
		return team_name;
	}

	public void setTeamName(String team_name) {
		this.team_name = team_name;
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
