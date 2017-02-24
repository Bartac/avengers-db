package io.avengers.domain;

public class Hero {
	int id;
	String name;
	Sex sex;
	long likes;
	long dislikes;

	public Hero(int id, String name, Sex sex, long likes, long dislikes) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.likes = likes;
		this.dislikes = dislikes;
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

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}

	public long getDislikes() {
		return dislikes;
	}

	public void setDislikes(long dislikes) {
		this.dislikes = dislikes;
	}

}
