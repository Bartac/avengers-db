package io.avengers.domain;

public class Team {
	int id;
	String name;
	byte[] picture;
	String history;

	public Team(int id, String name, byte[] picture, String history) {
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.history = history;
	}

	@Override
	public String toString() {
		return this.name;
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

}
