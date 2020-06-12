package com.liam.game.entities;

import java.awt.Graphics;
import java.util.LinkedList;

public class Projectile {
	
	private int x, y;
	private double xVel, yVel;
	LinkedList<HitBox> hitBoxes;
	
	public Projectile(int xSpawn, int ySpawn, double xVel, double yVel, LinkedList<HitBox> hitBoxes) {
		x = xSpawn;y = ySpawn;
		this.xVel = xVel;this.yVel = yVel;
		this.hitBoxes = hitBoxes;
	}
	
	public void tick(){
	}
	public void draw(Graphics g) {
		
	}
}
