package com.liam.game.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.liam.game.entities.Inventory;
import com.liam.game.entities.Player;
import com.liam.game.main.GamePanel;

public class PauseState extends GameState {
	

	private Inventory inventory;
	public PauseState(GameStateManager gsm, double xOffset, double yOffset) {
		super(gsm);
		GameState.xOffset = xOffset;
		GameState.yOffset = yOffset;
	}

	public void init() {

	}

	public void tick() {
		System.out.println((int)GameState.xOffset + " " + (int)GameState.yOffset);
	}

	public void draw(Graphics g) {
		g.setColor(new Color(50, 150, 200));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ESCAPE) {
			gsm.states.pop();//unpause
		}
	}

	public void keyReleased(int k) {
		
	}

}
