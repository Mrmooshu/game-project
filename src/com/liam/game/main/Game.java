package com.liam.game.main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Platformer"); // create the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x button closes window
		frame.setResizable(false); // window cannot be resized
		frame.setLayout(new BorderLayout());
		frame.add(new GamePanel(), BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
