package com.liam.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import com.liam.game.gamestate.GameState;
import com.liam.game.gamestate.Level1State;
import com.liam.game.main.GamePanel;
import com.liam.game.moveset.MoveSet;
import com.liam.game.moveset.MoveSet1;
import com.liam.game.objects.Block;
import com.liam.game.objects.MovingBlock;
import com.liam.game.physics.Collision;
import com.liam.game.resources.Animation;
import com.liam.game.resources.Images;

public class Player {

	//stats
	public int health = 100;
	
	private LinkedList<HitBox> hitBoxes;
	private LinkedList<HurtBox> hurtBoxes;
	private MoveSet equipedMoveSet = new MoveSet1(this);
	
	//movement booleans
	private boolean right = false, left = false, jumping = false, wallJumping = false, wallJumpingRight, wallJumpingLeft, falling = false;
	private boolean topCollision = false;
	private boolean wallSlidingRight = false, wallSlidingLeft = false;
	private boolean airDashing = false;
	private boolean crouching = false;
	private boolean runningRight = false, runningLeft = false;
	//bounds
	public double x, y;
	public int width, height;
	//move speed
	private double moveSpeed = 2;
	private double maxRunSpeed = 5;
	private double airDashFrameRight = 0;
	private double airDashFrameLeft = 0;
	private double xMomentum = 0;
	private double yMomentum = 0;
	//jump speed
	private double jumpSpeed = 4;
	private double currentJumpSpeed = jumpSpeed;
	//fall speed
	private double maxFallSpeed = 10;
	private double currentFallSpeed = 0.1;
	//input
	private boolean rightKeyDown = false;
	private boolean leftKeyDown = false;
	private int rightAirDashWindow = 0;
	private int leftAirDashWindow = 0;
	private boolean faceRight = true;
	private boolean faceLeft = false;
	private boolean stopMovement = false;
	//cooldown
	private int airDashCooldown = 0;
	private int wallJumpCooldown = 0;
	private boolean acting = false;
	//attacks
	private int invunerableFrames = 0;
	private boolean invunerable = false;

	//animations
	Animation rightIdle = new Animation(Images.playerIdleSprites, 6);
	Animation leftIdle = new Animation(Images.playerIdleSprites, 6);
	Animation rightWalk = new Animation(Images.playerWalkSprites, 4);
	Animation leftWalk = new Animation(Images.playerWalkSprites, 4);
	Animation rightJump = new Animation(Images.playerJumpSprites, 6);
	Animation leftJump = new Animation(Images.playerJumpSprites, 6);
	Animation rightFall = new Animation(Images.playerFallSprites, 2);
	Animation leftFall = new Animation(Images.playerFallSprites, 2);
	Animation rightCrouch = new Animation(Images.playerCrouchSprites, 4);
	Animation leftCrouch = new Animation(Images.playerCrouchSprites, 4);
	Animation rightWallslide = new Animation(Images.playerWallslideSprites, 8);
	Animation leftWallslide = new Animation(Images.playerWallslideSprites, 8);
	Animation rightRun = new Animation(Images.playerRunSprites, 2);
	Animation leftRun = new Animation(Images.playerRunSprites, 2);
	
	//hurtBoxes
	public HurtBox body = new HurtBox((int)x, (int)y, width, height);
	public HurtBox head = new HurtBox((int)x, (int)y, width, height);
	public HurtBox arms = new HurtBox((int)x, (int)y, width, height);
	
	public Player(int width, int height) {
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		this.width = width;
		this.height = height;
		hitBoxes = new LinkedList<HitBox>();
		hurtBoxes = new LinkedList<HurtBox>();
		hurtBoxes.add(body);hurtBoxes.add(head);hurtBoxes.add(arms);
	}
	
	public void tick(Block[] blocks, ArrayList<MovingBlock> movingBlocks, LinkedList<Enemy>enemies) {
		
		int intX = (int)x;
		int intY = (int)y;
		
		wallSlidingRight = false;
		wallSlidingLeft = false;
		if (rightAirDashWindow > 0) {
			rightAirDashWindow--;
			}
		if (leftAirDashWindow > 0) {
			leftAirDashWindow--;
			}
		if (airDashCooldown > 0) {
			airDashCooldown--;
		}
		if (wallJumpCooldown > 0) {
			wallJumpCooldown--;
		}
		
		//player block collision check
		for (int i = 0; i < blocks.length; i++) {
			// right and left
			for (int k = 0; k < height; k++) {
				//right collision
				if (Collision.playerBlock(new Point(intX + width + (int)GameState.xOffset, intY + k + (int)GameState.yOffset), blocks[i])) {
					right = false;
					stopMovement = true;
					if (rightKeyDown && falling) {
						wallSlidingRight = true;
					}
				}
				// collision correction right
				if (Collision.playerBlock(new Point(intX + width + (int)GameState.xOffset - 1, intY + k + (int)GameState.yOffset), blocks[i])){
					GameState.xOffset -= .1;
				}
				//left collision
				if (Collision.playerBlock(new Point(intX + (int)GameState.xOffset, intY + k + (int)GameState.yOffset), blocks[i])) {
					left = false;
					stopMovement = true;
					if (leftKeyDown && falling) {
						wallSlidingLeft = true;
					}
				}
				// collision correction left
				if (Collision.playerBlock(new Point(intX + (int)GameState.xOffset + 1, intY + k + (int)GameState.yOffset), blocks[i])) {
					GameState.xOffset += .1;
				}
			}
			// top and bottom
			for (int k = 0; k < width - 2; k++) {
				//top collision
				if (Collision.playerBlock(new Point(intX + k + (int)GameState.xOffset + 1, intY + (int)GameState.yOffset), blocks[i])) {
					jumping = false;
					falling = true;
				}
				// collision correction top
				if (Collision.playerBlock(new Point(intX + k + (int)GameState.xOffset + 1, intY + (int)GameState.yOffset - 1), blocks[i])) {
					GameState.yOffset += .1;
				}
				//bottom collision
				if (Collision.playerBlock(new Point(intX + k + (int)GameState.xOffset + 1, intY + height + (int)GameState.yOffset + 1), blocks[i])) {
					falling = false;
					topCollision = true;
					wallSlidingRight = false;
					wallSlidingLeft = false;
				}
				// collision correction bottom
				if (Collision.playerBlock(new Point(intX + k + (int)GameState.xOffset + 1, intY + height + (int)GameState.yOffset), blocks[i])) {
					GameState.yOffset -= .1;
				}
			}
			// fall check
			if (!topCollision && !jumping && !wallJumping) {
				falling = true;
			}
		}
		//player moving block collision check
		//needs to be updated to match collision above
		for (int i = 0; i < movingBlocks.size(); i++) {
			//right
			if (Collision.playerMovingBlock(new Point(intX + width + (int)GameState.xOffset, intY + (int)GameState.yOffset + 2), movingBlocks.get(i)) 
					|| Collision.playerMovingBlock(new Point(intX + width + (int)GameState.xOffset, intY + height + (int)GameState.yOffset - 1), movingBlocks.get(i))) {
				right = false;
			}
			//left
			if (Collision.playerMovingBlock(new Point(intX + (int)GameState.xOffset - 1, intY + (int)GameState.yOffset + 2), movingBlocks.get(i)) 
					|| Collision.playerMovingBlock(new Point(intX + (int)GameState.xOffset - 1, intY + height + (int)GameState.yOffset - 1), movingBlocks.get(i))) {
				left = false;
				airDashFrameLeft = 0;
			}
			//top
			if (Collision.playerMovingBlock(new Point(intX + (int)GameState.xOffset + 1, intY + (int)GameState.yOffset), movingBlocks.get(i)) 
					|| Collision.playerMovingBlock(new Point(intX + width + (int)GameState.xOffset - 2, intY + (int)GameState.yOffset), movingBlocks.get(i))) {
				jumping = false;
				falling = true;
			}
			//bottom
			if (Collision.playerMovingBlock(new Point(intX + (int)GameState.xOffset +2, intY + height + (int)GameState.yOffset + 1), movingBlocks.get(i)) 
					|| Collision.playerMovingBlock(new Point(intX + width + (int)GameState.xOffset - 2, intY + height + (int)GameState.yOffset + 1), movingBlocks.get(i))) {
				falling = false;
				topCollision = true;
				
				GameState.xOffset += movingBlocks.get(i).getMove();
			}
			else {
				if (!topCollision && !jumping) {
					falling = true;
				}
			}
		}
		//player collision with enemy hitboxes
		for (int i = 0; i < enemies.size(); i++) {
			for (int j = 0; j < enemies.get(i).getHitBoxes().size(); j++) {
				for (int k = 0; k < hurtBoxes.size(); k++) {
					if (!invunerable && Collision.hitBoxHurtBox(enemies.get(i).getHitBoxes().get(j), hurtBoxes.get(k))) {
						health -= enemies.get(i).getHitBoxes().get(j).getDamage();;
						invunerableFrames = 30;
						invunerable = true;
						xMomentum = enemies.get(i).getHitBoxes().get(j).getXKnockback();
						yMomentum = enemies.get(i).getHitBoxes().get(j).getYKnockback();
					}
				}
			}

			//player hitboxes collision with enemy
			for (int j = 0; j < hitBoxes.size(); j++) {
				for (int k = 0; k < enemies.get(i).getHurtBoxes().size(); k++) {
					if (Collision.hitBoxHurtBox(hitBoxes.get(j), enemies.get(i).getHurtBoxes().get(k))) {
						enemies.get(i).setXMomentum(- hitBoxes.get(j).getXKnockback());
						enemies.get(i).setYMomentum(- hitBoxes.get(j).getYKnockback());
						Level1State.hitStopTimer = hitBoxes.get(j).hitStun;
					}
				}
			}
		}
		
		if (Level1State.hitStopTimer == 0) {
			if (!stopMovement && airDashFrameRight > 0) {
				acting = true;
				xMomentum += 2;
				airDashFrameRight--;
				if (airDashFrameRight == 0) {
					acting = false;
				}
			}
			else if (!stopMovement && airDashFrameLeft > 0) {
				acting = true;
				xMomentum -= 2;
				airDashFrameLeft--;
				if (airDashFrameLeft == 0) {
					acting = false;
				}
			}
			else {
				airDashing = false;
			}
			if (!stopMovement  && airDashFrameRight == 1) {
				xMomentum = 10;
			}
			if (!stopMovement  && airDashFrameLeft == 1) {
				xMomentum = -10;
			}
			if (runningRight || runningLeft) {
				if (moveSpeed < maxRunSpeed)
				moveSpeed += .1;
			}
			else {
				moveSpeed = 2.5;
			}
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
				GameState.xOffset += xMomentum / 2;
				GameState.yOffset += yMomentum / 2;
			}

			
			if (right && rightKeyDown && !airDashing && !crouching && !wallJumping && !acting) GameState.xOffset += moveSpeed;
			if (left && leftKeyDown && !airDashing && !crouching && !wallJumping && !acting) GameState.xOffset -= moveSpeed;
			if (jumping) {
				GameState.yOffset -= currentJumpSpeed;
				currentJumpSpeed -= .1;
				crouching = false;
				runningRight = false;
				runningLeft = false;
				if (currentJumpSpeed <= 0) {
					currentJumpSpeed = jumpSpeed;
					jumping = false;
					falling = true;
				}
			}
			if (wallJumping) {
				GameState.yOffset -= currentJumpSpeed * 2;
				currentJumpSpeed -= .1;
				crouching = false;
				acting = true;
				falling = false;
				if (wallJumpingRight) {
					GameState.xOffset -= currentJumpSpeed * 1.2;
				}
				if (wallJumpingLeft) {
					GameState.xOffset += currentJumpSpeed * 1.2;
				}
				if (currentJumpSpeed <= 0) {
					currentJumpSpeed = jumpSpeed;
					wallJumping = false;
					wallJumpingRight =false;
					wallJumpingLeft = false;
					falling = true;
					acting = false;
				}
			}
			if (falling && !airDashing) {
				GameState.yOffset += currentFallSpeed;
				crouching = false;
				if (currentFallSpeed < maxFallSpeed) {
					currentFallSpeed += .1;
				}
			}
			if (!falling) {
				currentFallSpeed = .1;
			}
			if (wallSlidingRight || wallSlidingLeft) {
				maxFallSpeed = .5;
			}
			else {
				maxFallSpeed = 10;
			}
			
			if (rightKeyDown && !wallJumping) {
				right = true;
			}
			if (leftKeyDown && !wallJumping) {
				left = true;
			}
			topCollision = false;
			stopMovement = false;
			x = GamePanel.WIDTH / 2;
			y = GamePanel.HEIGHT / 2;
			
			if (invunerableFrames > 0) {
				acting = true;
				invunerableFrames--;
				if (invunerableFrames == 0) {
					acting = false;
				}
			}
			else {
				invunerable = false;
			}
		}
	}
	
	
	
	public void draw(Graphics g) {
		int drawX = (int)x + width/2 - 50;
		int drawY = (int)y - 31;
		int drawWidth = 100;
		int drawHeight = 80;
		
		if (invunerable) {
			g.setColor(Color.BLUE);
		}
	    else if (acting) {
			g.setColor(Color.GREEN);
		}
	    else if (runningLeft || runningRight) {
	    	g.setColor(Color.ORANGE);
	    }
		else {
			g.setColor(Color.RED);
		}
		//right idle and walk and run
		if (faceRight && !jumping && ! falling && !airDashing && !crouching && !wallJumping) {
			if (!right && !acting) {
				rightIdle.drawNextFrame(g, drawX, drawY, drawWidth, drawHeight);
				body.setBounds((int)x + 3, (int)y + 13, width - 11, height - 10);
				head.setBounds((int)x + 10, (int)y + 1, width - 20, height - 36);
				arms.setBounds((int)x - 2, (int)y + 15, width + 1, height - 36);
			}
			if (right && !acting && !runningRight) {
				rightWalk.drawNextFrame(g, drawX, drawY, drawWidth, drawHeight);
				body.setBounds((int)x - 2, (int)y + 13, width - 2, height - 10);
				head.setBounds((int)x + 8, (int)y, width - 20, height - 36);
				arms.setBounds((int)x, (int)y + 15, width, height - 32);
			}
			if (runningRight) {
				rightRun.drawNextFrame(g, drawX, drawY, drawWidth, drawHeight);
				body.setBounds((int)x - 2, (int)y + 30, width, height - 29);
				head.setBounds((int)x + 8, (int)y, width - 20, height - 36);
				arms.setBounds((int)x - 2, (int)y + 12, width - 4, height - 30);
			}
		}
		else {
			rightIdle.setFrameCounter(0);
			rightWalk.setFrameCounter(0);
			rightRun.setFrameCounter(0);
		}


		
		
		//left idle and walk and run
		if (faceLeft && !jumping && ! falling && !airDashing && !crouching && !wallJumping) {
			if (!left && !acting) {
				leftIdle.drawNextFrame(g, drawX + drawWidth, drawY, -drawWidth, drawHeight);
				body.setBounds((int)x + 6, (int)y + 13, width - 11, height - 10);
				head.setBounds((int)x + 8, (int)y + 1, width - 20, height - 36);
				arms.setBounds((int)x - 1, (int)y + 15, width + 1, height - 36);
			}
			if (left && !acting && !runningLeft) {
				leftWalk.drawNextFrame(g, drawX + drawWidth, drawY, -drawWidth, drawHeight);
				body.setBounds((int)x + 2, (int)y + 13, width - 2, height - 10);
				head.setBounds((int)x + 10, (int)y, width - 20, height - 36);
				arms.setBounds((int)x - 2, (int)y + 15, width, height - 32);
			}
			if (runningLeft) {
				leftRun.drawNextFrame(g, drawX + drawWidth, drawY, -drawWidth, drawHeight);
				body.setBounds((int)x, (int)y + 30, width, height - 29);
				head.setBounds((int)x + 10, (int)y, width - 20, height - 36);
				arms.setBounds((int)x + 4, (int)y + 12, width - 4, height - 30);
			}
		}
		else {
			leftIdle.setFrameCounter(0);
			leftWalk.setFrameCounter(0);
			leftRun.setFrameCounter(0);
		}

		
		
		//jump
		if (jumping || wallJumping) {
			if ((faceRight && !wallJumpingRight) || wallJumpingLeft) {
				rightJump.holdLastFrame(1);
				rightJump.drawNextFrame(g, drawX, drawY, drawWidth, drawHeight);
				body.setBounds((int)x + 7, (int)y + 22, width - 10, height - 29);
				head.setBounds((int)x + 8, (int)y - 6, width - 20, height - 36);
				arms.setBounds((int)x + 2, (int)y + 6, width - 4, height - 30);
			}
			if ((faceLeft && !wallJumpingLeft) || wallJumpingRight) {
				leftJump.holdLastFrame(1);
				leftJump.drawNextFrame(g, drawX + drawWidth, drawY, -drawWidth, drawHeight);
				body.setBounds((int)x + 1, (int)y + 22, width - 10, height - 29);
				head.setBounds((int)x + 10, (int)y - 6, width - 20, height - 36);
				arms.setBounds((int)x, (int)y + 6, width - 4, height - 30);
			}
		}
		else {
			rightJump.setFrameCounter(0);
			leftJump.setFrameCounter(0);
		}

		
		//fall
		if ((falling && !wallSlidingRight && !wallSlidingLeft) || (airDashing && !topCollision)) {
			if (faceRight) {
				rightFall.holdLastFrame(1);
				rightFall.drawNextFrame(g, drawX, drawY, drawWidth, drawHeight);
				body.setBounds((int)x + 7, (int)y + 22, width - 10, height - 29);
				head.setBounds((int)x + 8, (int)y - 6, width - 20, height - 36);
				arms.setBounds((int)x + 2, (int)y + 6, width - 4, height - 30);
			}
			if (faceLeft) {
				leftFall.holdLastFrame(1);
				leftFall.drawNextFrame(g, drawX + drawWidth, drawY, -drawWidth, drawHeight);
				body.setBounds((int)x + 1, (int)y + 22, width - 10, height - 29);
				head.setBounds((int)x + 10, (int)y - 6, width - 20, height - 36);
				arms.setBounds((int)x, (int)y + 6, width - 4, height - 30);
			}
		}
		else {
			rightFall.setFrameCounter(0);
			leftFall.setFrameCounter(0);
		}

		
		
		//wallsliding
		if (wallSlidingRight) {
			rightWallslide.drawNextFrame(g, drawX + drawWidth, drawY, -drawWidth, drawHeight);
			body.setBounds((int)x + 5, (int)y + 12, width - 5, height - 12);
			head.setBounds((int)x + 10, (int)y, width - 20, height - 36);
			arms.setBounds((int)x - 2, (int)y + 10, width - 2, height - 36);
		}
		else {
			rightWallslide.setFrameCounter(0);
		}
		if (wallSlidingLeft) {
			leftWallslide.drawNextFrame(g, drawX, drawY, drawWidth, drawHeight);
			body.setBounds((int)x - 2, (int)y + 12, width - 5, height - 12);
			head.setBounds((int)x + 8, (int)y, width - 20, height - 36);
			arms.setBounds((int)x + 2, (int)y + 10, width - 2, height - 36);
		}
		else {
			leftWallslide.setFrameCounter(0);
		}
		
		
		//crouching
		if (crouching) {
			if (faceRight) {
				rightCrouch.holdLastFrame(5);
				rightCrouch.drawNextFrame(g, drawX, drawY, drawWidth, drawHeight);
				body.setBounds((int)x, (int)y + 20, width - 9, height - 20);
				head.setBounds((int)x + 8, (int)y + 8, width - 20, height - 36);
				arms.setBounds((int)x - 4, (int)y + 22, width + 2, height - 38);
			}
			if (faceLeft) {
				leftCrouch.holdLastFrame(5);
				leftCrouch.drawNextFrame(g, drawX + drawWidth, drawY, -drawWidth, drawHeight);
				body.setBounds((int)x + 7, (int)y + 20, width - 9, height - 20);
				head.setBounds((int)x + 10, (int)y + 8, width - 20, height - 36);
				arms.setBounds((int)x, (int)y + 22, width + 2, height - 38);
			}
		}
		else {
			rightCrouch.setFrameCounter(0);
			leftCrouch.setFrameCounter(0);

		}

		
		
		//hurtbox
		for (int i = 0; i < hurtBoxes.size(); i++) {
			if (hurtBoxes.get(i).getActive()) {
				hurtBoxes.get(i).draw(g);
			}
		}
		
		//hitbox
		for (int i = 0; i < hitBoxes.size(); i++) {
			if (hitBoxes.get(i).getActiveFrames() != 0) {
				hitBoxes.get(i).draw(g);
				acting = true;
			}
			else {
				hitBoxes.remove(i);
				acting = false;
			}
		}
	}

	public LinkedList<HitBox> getHitBoxes() {
		return hitBoxes;
	}
	
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_D) {
			right = true;
			if (!rightKeyDown) {
				rightKeyDown = true;
				faceRight = true;
				faceLeft = false;
				if (rightAirDashWindow > 0 && airDashCooldown == 0 && jumping ||rightAirDashWindow > 0 && airDashCooldown == 0 && falling) {
					//airdash
					airDashing = true;
					jumping = false;
					airDashFrameRight = 10;
					airDashCooldown = 60;
				}
				else if (rightAirDashWindow > 0 && !falling && !jumping) {
					//run
					runningRight = true;
				}
				else {
					rightAirDashWindow = 30;
				}
				
			}

		}
		if (k == KeyEvent.VK_A) {
			left = true;
			if (!leftKeyDown) {
				leftKeyDown = true;
				faceLeft = true;
				faceRight = false;
				if (leftAirDashWindow > 0 && airDashCooldown == 0 && jumping || leftAirDashWindow > 0 && airDashCooldown == 0 && falling) {
					//airdash
					airDashing = true;
					jumping = false;
					airDashFrameLeft = 10;
					airDashCooldown = 60;
				}
				else if (leftAirDashWindow > 0 && !falling && !jumping) {
					//run
					runningLeft = true;
				}
				else {
					leftAirDashWindow = 30;
				}
			}
		}
		if (k == KeyEvent.VK_SPACE && !jumping && !falling && !wallJumping) {
			jumping = true;
		}
		if (k == KeyEvent.VK_SPACE && wallSlidingRight && falling && wallJumpCooldown == 0) {
			wallJumping = true;
			wallJumpingRight = true;
			wallJumpCooldown = 20;
		}
		if (k == KeyEvent.VK_SPACE && wallSlidingLeft && falling && wallJumpCooldown == 0) {
			wallJumping = true;
			wallJumpingLeft = true;
			wallJumpCooldown = 20;
		}
		if (k == KeyEvent.VK_S && !jumping && !falling) {
			crouching = true;
		}
		if (k == KeyEvent.VK_G && !acting && !jumping && !falling && !crouching) {
			if (faceRight && !acting) {
				equipedMoveSet.buttonA_StandingRight();
			}
			if (faceLeft && !acting) {
				equipedMoveSet.buttonA_StandingLeft();
			}
		}
		if (k == KeyEvent.VK_G && !acting && !jumping && !falling && crouching) {
			if (faceRight && !acting) {
				equipedMoveSet.buttonA_CrouchingRight();
			}
			if (faceLeft && !acting) {
				equipedMoveSet.buttonA_CrouchingLeft();
			}
		}
	}

	public void keyReleased(int k) {
		if (k == KeyEvent.VK_D) {
			right = false;
			rightKeyDown = false;
			runningRight = false;
		}
		if (k == KeyEvent.VK_A) {
			left = false;
			leftKeyDown = false;
			runningLeft = false;
		}
		if (k == KeyEvent.VK_S) {
			crouching = false;
		}
	}
}
