package com.liam.game.entities;

import java.awt.Graphics;

public abstract class Enemy {
	private double x, y;
	private int width, height;
	
	public Enemy(int width, int height, int xSpawn, int ySpawn) {
		x = xSpawn;
		y = ySpawn;
		this.width = width;
		this.height = height;
	}
	
	public abstract void tick();
	public abstract void draw(Graphics g);
}
