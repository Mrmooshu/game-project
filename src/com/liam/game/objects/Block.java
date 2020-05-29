package com.liam.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.liam.game.gamestate.GameState;
import com.liam.game.resources.Images;

public class Block extends Rectangle {
	private static final long serialVersionUID = 1L;

	public static final int blockSize = 8;
	private int id;
	
	public Block(int x, int y, int id) {
		setBounds(x, y, blockSize, blockSize);
		this.id = id;
	}
	
	public void tick() {
	}
	
	public void draw(Graphics g) {
		if (id != 0) {
			g.drawImage(Images.blocks[id -1], x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height, null);
		}
	}
	
	//getters and setters
	public void setID(int id) {
		this.id =id;
	}
	
	public int getID() {
		return id;
	}
}
