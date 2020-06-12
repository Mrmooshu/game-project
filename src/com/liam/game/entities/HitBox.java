package com.liam.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HitBox extends Rectangle {
	private static final long serialVersionUID = 1L;
	private int activeFrames;
	private int xKnockback;
	private int yKnockback;
	public int hitStun;
	private int damage;

	public HitBox(int x, int y, int width, int height, int activeFrames, int hitStun, int damage, int xKnockback, int yKnockback) {
		setBounds(x, y, width, height);
		this.activeFrames = activeFrames;
		this.xKnockback = xKnockback;
		this.yKnockback = yKnockback;
		this.hitStun = hitStun;
		this.damage = damage;
		}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawRect(x, y, width, height);
		if (activeFrames > 0) {
			activeFrames--;
		}
	}
	
	public int getActiveFrames() {
		return activeFrames;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
	public int getDamage() {
		return damage;
	}
	public int getXKnockback() {
		return xKnockback;
	}
	public int getYKnockback() {
		return yKnockback;
	}
}
