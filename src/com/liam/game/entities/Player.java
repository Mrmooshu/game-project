package com.liam.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.liam.game.gamestate.GameState;
import com.liam.game.main.GamePanel;
import com.liam.game.objects.Block;
import com.liam.game.objects.MovingBlock;
import com.liam.game.physics.Collision;
import com.liam.game.resources.Images;

public class Player {

	//movement booleans
	private boolean right = false, left = false, jumping = false, wallJumping = false, falling = false;
	private boolean topCollision = false;
	private boolean wallSlidingRight = false, wallSlidingLeft = false;
	private boolean airDashing = false;
	//bounds
	private double x, y;
	private int width, height;
	//move speed
	private double moveSpeed = 2.5;
	private double runSpeed = 4.5;
	private double dashFrameRight = 0;
	private double dashFrameLeft = 0;
	private double xMomentum = 0;
	private double yMomentum = 0;
	//jump speed
	private double jumpSpeed = 3;
	private double currentJumpSpeed = jumpSpeed;
	//fall speed
	private double maxFallSpeed = 10;
	private double currentFallSpeed = 0.1;
	//input
	private boolean rightKeyDown = false;
	private boolean leftKeyDown = false;
	private int rightDashWindow = 0;
	private int leftDashWindow = 0;
	private boolean faceRight = true;
	private boolean faceLeft = false;
	private boolean stopMovement = false;
	//cooldown
	private int dashCooldown = 0;

	
	public Player(int width, int height) {
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		this.width = width;
		this.height = height;
	}
	
	public void tick(Block[][] blocks, ArrayList<MovingBlock> movingBlocks) {
		
		int intX = (int)x;
		int intY = (int)y;
		
		wallSlidingRight = false;
		wallSlidingLeft = false;
		if (rightDashWindow > 0) {
			rightDashWindow--;
			}
		if (leftDashWindow > 0) {
			leftDashWindow--;
			}
		if (dashFrameRight > 0) {
			dashFrameRight--;
		}
		if (dashFrameLeft > 0) {
			dashFrameLeft--;
		}
		if (dashCooldown > 0) {
			dashCooldown--;
		}
		
		//player block collision check
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[0].length; j++) {
				// check if block has collision
				if (blocks[i][j].getID() != 0) {
					//right
					if (Collision.playerBlock(new Point(intX + width + (int)GameState.xOffset, intY + (int)GameState.yOffset + 2), blocks[i][j]) 
							|| Collision.playerBlock(new Point(intX + width + (int)GameState.xOffset, intY + height + (int)GameState.yOffset - 1), blocks[i][j])) {
						right = false;
						stopMovement = true;
						if (rightKeyDown && falling) {
							wallSlidingRight = true;
						}
					}
					//left
					if (Collision.playerBlock(new Point(intX + (int)GameState.xOffset, intY + (int)GameState.yOffset + 2), blocks[i][j]) 
							|| Collision.playerBlock(new Point(intX + (int)GameState.xOffset, intY + height + (int)GameState.yOffset - 1), blocks[i][j])) {
						left = false;
						stopMovement = true;
						if (leftKeyDown && falling) {
							wallSlidingLeft = true;
						}
					}
					//top
					if (Collision.playerBlock(new Point(intX + (int)GameState.xOffset + 1, intY + (int)GameState.yOffset), blocks[i][j]) 
							|| Collision.playerBlock(new Point(intX + width + (int)GameState.xOffset - 2, intY + (int)GameState.yOffset), blocks[i][j])) {
						jumping = false;
						falling = true;
					}
					//bottom
					if (Collision.playerBlock(new Point(intX + (int)GameState.xOffset + 2, intY + height + (int)GameState.yOffset + 1), blocks[i][j]) 
							|| Collision.playerBlock(new Point(intX + width + (int)GameState.xOffset - 2, intY + height + (int)GameState.yOffset + 1), blocks[i][j])) {
						y = blocks[i][j].getY() - height - GameState.yOffset;
						falling = false;
						topCollision = true;
					}
					else {
						if (!topCollision && !jumping && !wallJumping) {
							falling = true;
						}
					}
				}
			}
		}
		//player moving block collision check
		for (int i = 0; i < movingBlocks.size(); i++) {
			if (movingBlocks.get(i).getID() != 0) {
				if (movingBlocks.get(i).getID() != 0) {
					//right
					if (Collision.playerMovingBlock(new Point(intX + width + (int)GameState.xOffset, intY + (int)GameState.yOffset + 2), movingBlocks.get(i)) 
							|| Collision.playerMovingBlock(new Point(intX + width + (int)GameState.xOffset, intY + height + (int)GameState.yOffset - 1), movingBlocks.get(i))) {
						right = false;
					}
					//left
					if (Collision.playerMovingBlock(new Point(intX + (int)GameState.xOffset - 1, intY + (int)GameState.yOffset + 2), movingBlocks.get(i)) 
							|| Collision.playerMovingBlock(new Point(intX + (int)GameState.xOffset - 1, intY + height + (int)GameState.yOffset - 1), movingBlocks.get(i))) {
						left = false;
						dashFrameLeft = 0;
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
						y = movingBlocks.get(i).getY() - height - GameState.yOffset;
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
			}
		}
		if (!stopMovement  && dashFrameRight > 0) {
			xMomentum += 3;
		}
		else if (!stopMovement  && dashFrameLeft > 0) {
			xMomentum -= 3;
		}
		else {
			airDashing = false;
		}
		if (!stopMovement  && dashFrameRight == 1) {
			xMomentum = 10;
		}
		if (!stopMovement  && dashFrameLeft == 1) {
			xMomentum = -10;
		}
		if (xMomentum > 0) {
			xMomentum -= .2;
		}
		if (xMomentum < 0) {
			xMomentum += .2;
		}
		if (topCollision) {
			xMomentum *= .4;
			yMomentum *= .7;
		}
		
		topCollision = false;
		
		if (!stopMovement) {
			GameState.xOffset += xMomentum / 2;
			GameState.yOffset += yMomentum / 2;
		}
		if (right && rightKeyDown && !airDashing) GameState.xOffset += moveSpeed;
		if (left && leftKeyDown && !airDashing) GameState.xOffset -= moveSpeed;
		if (jumping) {
			GameState.yOffset -= currentJumpSpeed;
			currentJumpSpeed -= .1;
			if (currentJumpSpeed <= 0) {
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}
		}
		if (wallJumping) {
			GameState.yOffset -= currentJumpSpeed * 2;
			currentJumpSpeed -= .1;
			if (currentJumpSpeed <= 0) {
				currentJumpSpeed = jumpSpeed;
				wallJumping = false;
				falling = true;
			}
		}
		if (falling && !airDashing) {
			GameState.yOffset += currentFallSpeed;
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
		if (!wallSlidingRight || wallSlidingLeft) {
			maxFallSpeed = 10;
		}
		stopMovement = false;
	}
	
	public void draw(Graphics g) {
		if (faceRight && !falling && !airDashing) {
			g.drawImage(Images.playerSprites[0], (int)x, (int)y, width, height, null);
		}
		else if (faceLeft && !falling && !airDashing) {
			g.drawImage(Images.playerSprites[1], (int)x, (int)y, width, height, null);
		}
		else if ((faceRight && falling) || (faceRight && airDashing && !topCollision)) {
			g.drawImage(Images.playerSprites[4], (int)x, (int)y, width, height, null);
		}
		else if ((faceLeft && falling) || (faceLeft && airDashing && !topCollision)) {
			g.drawImage(Images.playerSprites[5], (int)x, (int)y, width, height, null);
		}
	}
	
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_D) {
			right = true;
			if (!rightKeyDown) {
				rightKeyDown = true;
				faceRight = true;
				faceLeft = false;
				if (rightDashWindow > 0 && dashCooldown == 0 && jumping ||rightDashWindow > 0 && dashCooldown == 0 && falling) {
					//dash
					airDashing = true;
					jumping = false;
					dashFrameRight = 10;
					dashCooldown = 60;
				}
				else {
					rightDashWindow = 30;
				}
			}

		}
		if (k == KeyEvent.VK_A) {
			left = true;
			if (!leftKeyDown) {
				leftKeyDown = true;
				faceLeft = true;
				faceRight = false;
				if (leftDashWindow > 0 && dashCooldown == 0 && jumping || leftDashWindow > 0 && dashCooldown == 0 && falling) {
					//dash
					airDashing = true;
					jumping = false;
					dashFrameLeft = 10;
					dashCooldown = 60;
				}
				else {
					leftDashWindow = 30;
				}
			}
		}
		if (k == KeyEvent.VK_SPACE && !jumping && !falling) {
			jumping = true;
		}
		if (k == KeyEvent.VK_SPACE && wallSlidingRight && falling) {
			wallJumping = true;
			falling = false;
			xMomentum -= 5;
			currentFallSpeed = 0.1;
			GameState.xOffset -= 0.1;
		}
		if (k == KeyEvent.VK_SPACE && wallSlidingLeft && falling) {
			wallJumping = true;
			falling = false;
			xMomentum += 5;
			currentFallSpeed = 0.1;
			GameState.xOffset += 0.1;
		}
	}

	public void keyReleased(int k) {
		if (k == KeyEvent.VK_D) {
			right = false;
			rightKeyDown = false;
		}
		if (k == KeyEvent.VK_A) {
			left = false;
			leftKeyDown = false;
		}
	}
}
