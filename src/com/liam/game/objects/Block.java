package com.liam.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.liam.game.gamestate.GameState;
import com.liam.game.resources.Images;

public class Block extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public Block(int x, int y, int width, int height) {
		setBounds(x, y, width, height);
	}
	
	public void tick() {
	}
	
	public void draw(Graphics g) {
		g.drawImage(Images.blocks[0], x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height, null);
	}
}
