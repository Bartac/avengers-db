package io.avengers.ui;

import io.avengers.service.TeamService;

public class TestApplication {

	public static void main(String[] args) throws Exception {
		TeamService Service = new TeamService();
		System.out.println(Service.findAll());
	}
}
