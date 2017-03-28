package io.avengers.ui;

import java.awt.GridLayout;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import io.avengers.domain.Hero;

public class HeroPanel extends JPanel {
	private JLabel lblName;
	private JLabel lblName1;
	private JLabel lblName2;
	private JLabel lblName3;
	private JLabel lblName4;
	private JPanel name;
	private JPanel sex;
	private JPanel realname;
	private JPanel moviename;
	private JPanel teamname;

	/**
	 * Create the panel.
	 */
	public HeroPanel() {

		moviename = new JPanel();
		moviename.setLayout(new BoxLayout(moviename, BoxLayout.Y_AXIS));

		lblName3 = new JLabel("Movies");
		moviename.add(lblName3);
		setLayout(new GridLayout(0, 5, 0, 0));

		name = new JPanel();
		name.setLayout(new BoxLayout(name, BoxLayout.Y_AXIS));

		lblName = new JLabel("Name");
		name.add(lblName);
		add(name);

		sex = new JPanel();
		sex.setLayout(new BoxLayout(sex, BoxLayout.Y_AXIS));

		lblName1 = new JLabel("Sex");
		sex.add(lblName1);
		add(sex);

		realname = new JPanel();
		realname.setLayout(new BoxLayout(realname, BoxLayout.Y_AXIS));

		lblName2 = new JLabel("Real Name");
		realname.add(lblName2);
		add(realname);
		add(moviename);

		teamname = new JPanel();
		add(teamname);
		teamname.setLayout(new BoxLayout(teamname, BoxLayout.Y_AXIS));

		lblName4 = new JLabel("Team");
		teamname.add(lblName4);

	}

	public void addHeroes(Set<Hero> heroes) {
		Hero tmp = new Hero("Temp");
		for (Hero h : heroes) {
			for (String s : h.getMovies_name()) {

				if (h.getName() == tmp.getName()) {
					getPanel().add(new JLabel("-"));
					getSex().add(new JLabel("-"));
				} else {
					getPanel().add(new JLabel(h.getName()));
					getSex().add(new JLabel(h.getSex().toString()));
				}

				if (h.getReal_name() == null || h.getReal_name() == tmp.getReal_name()) {
					getRealname().add(new JLabel("-"));
				} else {
					getRealname().add(new JLabel(h.getReal_name()));

				}

				if (s == null) {
					getMoviename().add(new JLabel("-"));
				} else {
					getMoviename().add(new JLabel(s));
				}

				if (h.getTeam_name() == null || h.getTeam_name() == tmp.getTeam_name()) {
					getTeamname().add(new JLabel("-"));
				} else {
					getTeamname().add(new JLabel(h.getTeam_name()));
				}
				tmp = h;
			}

		}
	}

	public JPanel getPanel() {
		return name;
	}

	public JPanel getSex() {
		return sex;
	}

	public JPanel getRealname() {
		return realname;
	}

	public JPanel getMoviename() {
		return moviename;
	}

	public JPanel getTeamname() {
		return teamname;
	}
}
