package com.liam.game.main;

import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 11;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	// constructor
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	

}
