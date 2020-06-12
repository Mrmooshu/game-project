package com.liam.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import com.liam.game.gamestate.GameState;
import com.liam.game.gamestate.Level1State;
import com.liam.game.main.GamePanel;
import com.liam.game.objects.Block;
import com.liam.game.objects.MovingBlock;
import com.liam.game.physics.Collision;
import com.liam.game.resources.Images;

public class Enemy{
	
	private LinkedList<HitBox> hitBoxes;
	private LinkedList<HurtBox> hurtBoxes;
		
	//stats
	private int health;
	boolean die = false;
	
	//movement
	private boolean right = false, left = false, jumping = false, falling = false;
	private boolean topCollision = false;
	private double moveSpeed = .6;
	private double xMomentum = 0;
	private double yMomentum = 0;
	private boolean stopMovement = false;
	
	//jump speed
	private double jumpSpeed = 4;
	private double currentJumpSpeed = jumpSpeed;
	//fall speed
	private double maxFallSpeed = 4;
	private double currentFallSpeed = 0.1;
	
	//cords
	private double x, y;
	private int width, height;
	private boolean playerLeft = false;
	private boolean playerRight = false;
	
	//cooldown
	private int jumpCooldown;
	
	public Enemy(int width, int height, double xSpawn, double ySpawn, int health) {
		this.width = width;
		this.height = height;
		this.x = xSpawn;
		this.y = ySpawn;
		this.health = health;
		hitBoxes = new LinkedList<HitBox>();
		hitBoxes.add(new HitBox((int)x + 5 - (int)GameState.xOffset, (int)y + 5 - (int)GameState.yOffset, width - 10, height - 10, -1, 50, 10, 8, -8));
		hurtBoxes = new LinkedList<HurtBox>();
		hurtBoxes.add(new HurtBox((int)x + 5 - (int)GameState.xOffset, (int)y + 5 - (int)GameState.yOffset, width - 10, height - 10));
	}
	
	public void tick(Block[] blocks, ArrayList<MovingBlock> movingBlocks) {
		
		int intX = (int)x;
		int intY = (int)y;
		
		for (int i = 0; i < blocks.length; i++) {
			// right and left
			for (int k = 0; k < height; k++) {
				//right collision
				if (Collision.playerBlock(new Point(intX + width, intY + k), blocks[i])) {
					right = false;
					stopMovement = true;
				}
				// collision correction right
				if (Collision.playerBlock(new Point(intX + width - 1, intY + k), blocks[i])){
					x -= 1;
				}
				//left collision
				if (Collision.playerBlock(new Point(intX, intY + k), blocks[i])) {
					left = false;
				}
				// collision correction left
				if (Collision.playerBlock(new Point(intX + 1, intY + k), blocks[i])) {
					x += 1;
				}
			}
			// top and bottom
			for (int k = 0; k < width - 2; k++) {
				//top collision
				if (Collision.playerBlock(new Point(intX + k + 1, intY), blocks[i])) {
					jumping = false;
					falling = true;
				}
				// collision correction top
				if (Collision.playerBlock(new Point(intX + k + 1, intY - 1), blocks[i])) {
					y += .1;
				}
				//bottom collision
				if (Collision.playerBlock(new Point(intX + k + 1, intY + height + 1), blocks[i])) {
					falling = false;
					topCollision = true;
				}
				// collision correction bottom
				if (Collision.playerBlock(new Point(intX + k + 1, intY + height), blocks[i])) {
					y -= .1;
				}
			}
			// fall check
			if (!topCollision && !jumping) {
				falling = true;
			}
		}
		
		if (Level1State.hitStopTimer == 0) {
			//friction
			if (xMomentum > 0) {
				xMomentum -= .2;
			}
			if (xMomentum < 0) {
				xMomentum += .2;
			}
			if (yMomentum > 0) {
				yMomentum -= .2;
			}
			if (yMomentum < 0) {
				yMomentum += .2;
			}
			if (topCollision) {
				xMomentum *= .9;
				yMomentum *= .7;
			}
			if (!stopMovement) {
				x += xMomentum / 2;
				y += yMomentum / 2;
			}
			
			if (jumpCooldown > 0) {
				jumpCooldown--;
			}
			
			if (GameState.xOffset + GamePanel.WIDTH / 2 <= x + width / 2) {
				playerLeft = true;
				playerRight = false;
			}
			else if (GameState.xOffset + GamePanel.WIDTH / 2 >= x + width / 2) {
				playerRight = true;
				playerLeft = false;
			}
			else {
				playerRight = false;
				playerLeft = false;
			}
			if (playerLeft && topCollision) {
				x -= moveSpeed;
			}
			if (playerRight && topCollision) {
				x += moveSpeed;
			}
			
			if (jumping) {
				y -= currentJumpSpeed;
				currentJumpSpeed -= .1;
				if (currentJumpSpeed <= 0) {
					currentJumpSpeed = jumpSpeed;
					jumping = false;
					falling = true;
				}
			}
			if (falling) {
				y += currentFallSpeed;
				if (currentFallSpeed < maxFallSpeed) {
					currentFallSpeed += .1;
				}
			}
			
			if (x + width / 2 - 100 <= GameState.xOffset + GamePanel.WIDTH / 2 && playerLeft && topCollision && jumpCooldown == 0) {
				jumping = true;
				jumpCooldown = 100;
				xMomentum -= 10;
			}
			if ( x + width / 2 + 100 >= GameState.xOffset + GamePanel.WIDTH / 2 && playerRight && topCollision && jumpCooldown == 0) {
				jumping = true;
				jumpCooldown = 100;
				xMomentum += 10;
			}
			
			stopMovement = false;
			topCollision = false;
			
			if (health <= 0) {
				die = true;
			}
		}	
	}
	public void draw(Graphics g) {
		g.drawImage(Images.blocks[1], (int)x - (int)GameState.xOffset, (int)y - (int)GameState.yOffset, width, height, null);

		//hurtboxes
		for (int i = 0; i < hurtBoxes.size(); i++) {
			if (hurtBoxes.get(i).getActive()) {
				hurtBoxes.get(i).setBounds((int)x + 5 - (int)GameState.xOffset, (int)y + 5 - (int)GameState.yOffset, width - 10, height - 10);
				hurtBoxes.get(i).draw(g);
			}
		}
		//hitbox
		for (int i = 0; i < hitBoxes.size(); i++) {
			if (hitBoxes.get(i).getActiveFrames() != 0) {
				hitBoxes.get(i).setBounds((int)x + 5 - (int)GameState.xOffset, (int)y + 5 - (int)GameState.yOffset, width - 10, height - 10);
				hitBoxes.get(i).draw(g);
			}
			else {
				hitBoxes.remove(i);
			}
		}

	}
	
	public LinkedList<HurtBox> getHurtBoxes() {
		return hurtBoxes;
	}
	public LinkedList<HitBox> getHitBoxes() {
		return hitBoxes;
	}
	public double getX() {
		return x;
	}
	public void setXMomentum(double x) {
		this.xMomentum = x;
	}
	public void setYMomentum(double y) {
		this.yMomentum = y;
	}
	public boolean getDie() {
		return die;
	}
}
