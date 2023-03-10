package com.honey.neon.window;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {

	public Window(int w, int h, String title, Game game) {
		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));

		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}
	
}
