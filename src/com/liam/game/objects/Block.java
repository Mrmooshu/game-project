package com.liam.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.liam.game.gamestate.GameState;

public class Block extends Rectangle {
	private static final long serialVersionUID = 1l;

	public static final int blockSize = 64;
	private int id;
	
	public Block(int x, int y, int id) {
		setBounds(x, y, blockSize, blockSize);
		this.id = id;
	}
	
	public void tick() {
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		if (id != 0) {
			g.fillRect(x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height);

		}
	}
}
