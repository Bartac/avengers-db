package io.avengers.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import io.avengers.service.HeroService;

public class AvengersFrame extends JFrame {

	private HeroPanel heroPanel;

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
		setBounds(100, 100, 800, 800);
		heroPanel = new HeroPanel();
		heroPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//heroPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(heroPanel);
		HeroService service = new HeroService();
		heroPanel.addHeroes(service.findAll());
		System.out.println(service.findAll());
		
	}

}
