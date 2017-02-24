package io.avengers.domain;

import java.util.Date;

public class Movie {
	int id;
	String name;
	byte[] picture;
	String history;
	Date date;

	public Movie(int id, String name, byte[] picture, String history, Date date) {
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.history = history;
		this.date = date;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
