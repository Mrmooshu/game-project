package com.liam.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.liam.game.gamestate.GameState;
import com.liam.game.resources.Images;

public class MovingBlock extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	private int leftBound, rightBound;
	private int move = 1;
	
	public MovingBlock(int x, int y, int width, int height, int leftBound, int rightBound) {
		setBounds(x, y, width, height);
		this.rightBound = rightBound;
		this.leftBound = leftBound;
	}
	
	public void tick() {
		if (x + width - GameState.xOffset >= rightBound - GameState.xOffset && move != -1) {
			move *= -1;
		}
		if (x - GameState.xOffset <= leftBound - GameState.xOffset && move != 1) {
			move *= -1;
		}
		
		x += move;
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(Images.blocks[0], x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height, null);
	}
	
	public int getMove() {
		return move;
	}
}
