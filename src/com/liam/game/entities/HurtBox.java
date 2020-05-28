package com.liam.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HurtBox extends Rectangle{
	private static final long serialVersionUID = 1L;
	private boolean active;

	public HurtBox(int x, int y, int width, int height) {
		setBounds(x, y, width, height);
		active = true;
		}
	
	public void tick(int x, int y, int width, int height) {
		setBounds(x, y, width, height);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
	}
	
	public boolean getActive() {
		return active;
	}
	
	public void toggleActive() {
		if (active) {
			active = false;
		}
		if (!active) {
			active = true;
		}
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
