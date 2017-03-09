package io.avengers.ui;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import io.avengers.service.HeroService;

public class AvengersFrame extends JFrame {

	private HeroInfo heroPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AvengersFrame frame = new AvengersFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AvengersFrame() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		heroPanel = new HeroInfo();
		heroPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//heroPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(heroPanel);
		HeroService service = new HeroService();
		heroPanel.addHero(service.findHeroesByName("Captain America"));
		heroPanel.getBtnSearch().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HeroInfo pan = new HeroInfo();
				pan.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(pan);
				pan.addHero(service.findHeroesByName(heroPanel.getTextField_1().toString()));
			}
		});
	}

}
