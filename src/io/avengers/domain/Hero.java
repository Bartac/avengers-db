package io.avengers.domain;

public class Hero {
	int id;
	String name;
	Sex sex;
	byte[] picture;
	String abilities;
	String history;

	public Hero(int id, String name, Sex sex, byte[] picture, String abilities, String history) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.picture = picture;
		this.abilities = abilities;
		this.history = history;
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

}
