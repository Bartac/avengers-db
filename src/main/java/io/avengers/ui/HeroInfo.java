package io.avengers.ui;

import java.awt.Color;
import java.awt.Panel;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import io.avengers.domain.Hero;
import io.avengers.service.HeroService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeroInfo extends JPanel {

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
	private final JTextField textField = new JTextField();
	private JTextField textField_1;
	private JPanel lblNewLabel_1;
	private JPanel label_3;
	private JPanel label;
	private JPanel label_1;
	private JPanel label_2;
	private JPanel label_4;
	private JPanel label_5;
	private JPanel label_6;
	private Panel panel;
	private JLabel lblMovies_1;
	private JButton btnSearch;

	/**
	 * Create the panel.
	 */
	public HeroInfo() {
		
		JLabel lblNewLabel = new JLabel("Real name");
		lblNewLabel.setBounds(227, 83, 63, 33);
		
		textField_1 = new JTextField();
		textField_1.setBounds(227, 12, 130, 20);
		textField_1.setColumns(10);
		
		btnSearch = new JButton("SEARCH");

		
		btnSearch.setBounds(367, 11, 73, 23);
		setLayout(null);
		add(lblNewLabel);
		add(textField_1);
		add(btnSearch);
		
		label = new JPanel();
		label.setBounds(227, 51, 213, 33);
		add(label);
		
		label_1 = new JPanel();
		label_1.setBounds(300, 83, 140, 33);
		add(label_1);
		
		label_2 = new JPanel();
		label_2.setBounds(300, 116, 140, 33);
		add(label_2);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setBounds(227, 116, 63, 33);
		add(lblSex);
		
		label_4 = new JPanel();
		label_4.setBounds(300, 148, 140, 33);
		add(label_4);
		
		JLabel lblReam = new JLabel("Team");
		lblReam.setBounds(227, 148, 63, 33);
		add(lblReam);
		
		label_5 = new JPanel();
		label_5.setBounds(300, 180, 140, 33);
		add(label_5);
		
		JLabel lblAbilities = new JLabel("Abilities");
		lblAbilities.setBounds(227, 180, 63, 33);
		add(lblAbilities);
		
		label_6 = new JPanel();
		label_6.setBounds(300, 213, 140, 76);
		
		JLabel lblMovies = new JLabel("Movies");
		lblMovies.setBounds(227, 213, 63, 76);
		add(lblMovies);
		
		label_3 = new JPanel();
		label_3.setBounds(14, 180, 203, 109);
		add(label_3);
		
		JLabel lblStory = new JLabel("Story");
		lblStory.setHorizontalAlignment(SwingConstants.CENTER);
		lblStory.setBounds(14, 154, 203, 20);
		add(lblStory);
		
		lblNewLabel_1 = new JPanel();
		lblNewLabel_1.setForeground(Color.MAGENTA);
		lblNewLabel_1.setBounds(14, 15, 203, 134);
		add(lblNewLabel_1);
		
		panel = new Panel();
		panel.setBounds(300, 214, 140, 76);
		add(panel);
		
		lblMovies_1 = new JLabel("Movies");
		panel.add(lblMovies_1);


	}

	public void addHero(Set<Hero> hero) {
		for (Hero h : hero){
			getHeroName().add(new JLabel(h.getName()));
			getRealName().add(new JLabel(h.getReal_name()));
			getSex().add(new JLabel(h.getSex().toString()));
			getTeamname().add(new JLabel(h.getTeam_name()));
			getStory().add(new JLabel(h.getHistory()));
			for (String s : h.getMovies_name()){
				getMovie().add(new JLabel(s));
			}
		}
	}
	
	public JPanel getRealName() {
		return label_1;
	}
	public JPanel getSex() {
		return label_2;
	}
	public JPanel getTeam() {
		return label_4;
	}
	public JPanel getTeamname() {
		return label_5;
	}
	public Panel getMovie() {
		return panel;
	}
	public JPanel getPicture() {
		return lblNewLabel_1;
	}
	public JPanel getStory() {
		return label_3;
	}
	public JPanel getHeroName() {
		return label;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public JButton getBtnSearch() {
		return btnSearch;
	}
}

