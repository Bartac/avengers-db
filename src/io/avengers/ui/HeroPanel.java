package io.avengers.ui;

import java.awt.FlowLayout;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import io.avengers.domain.Hero;

public class HeroPanel extends JPanel {
	private JLabel lblName;
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public HeroPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		lblName = new JLabel("Name");
		panel.add(lblName);
	}

	public void addHeroes(Set<Hero> heroes) {
		for (Hero h : heroes) {
			JLabel label = new JLabel(h.getName());
			getPanel().add(label);
		}
	}


	public JPanel getPanel() {
		return panel;
	}
}
