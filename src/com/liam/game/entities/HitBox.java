package com.liam.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HitBox extends Rectangle {
	private static final long serialVersionUID = 1L;
	private int activeFrames;
	private int xKnockback;
	private int yKnockback;

	public HitBox(int x, int y, int width, int height, int activeFrames, int xKnockback, int yKnockback) {
		setBounds(x, y, width, height);
		this.activeFrames = activeFrames;
		this.xKnockback = xKnockback;
		this.yKnockback = yKnockback;
		}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		activeFrames--;
	}
	
	public int getActiveFrames() {
		return activeFrames;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
	
	public int getXKnockback() {
		return xKnockback;
	}
	public int getYKnockback() {
		return yKnockback;
	}
}
