package com.liam.game.moveset;

import com.liam.game.entities.HitBox;
import com.liam.game.entities.Player;

public class MoveSet1 extends MoveSet{

	
	
	public MoveSet1(Player player) {
		super(player);
	}
	
	public void buttonA_StandingRight() {
		int startUpFrames = 10;
		int activeFrames = 20;
		int recoverFrames = 10;
		int hitStun = 20;
		int damage = 10;
		int xKnockback = -10;
		int yKnockback = -10;
		int width = 50;
		int height = 20;
		player.getHitBoxes().add(new HitBox((int)player.x + width / 2, (int)player.y + 15, width, height, activeFrames, hitStun, damage, xKnockback, yKnockback));
		
	}
	public void buttonA_StandingLeft() {
		int activeFrames = 20;
		int hitStun = 20;
		int damage = 10;
		int xKnockback = 10;
		int yKnockback = -10;
		int width = 50;
		int height = 20;
		player.getHitBoxes().add(new HitBox((int)player.x - width / 2 - player.width / 2, (int)player.y + 15, width, height, activeFrames, hitStun, damage, xKnockback, yKnockback));
	}
	public void buttonA_CrouchingRight() {
		int activeFrames = 20;
		int hitStun = 40;
		int damage = 10;
		int xKnockback = 10;
		int yKnockback = -5;
		player.getHitBoxes().add(new HitBox((int)player.x + 20, (int)player.y + 22, 50, 10, activeFrames, hitStun,damage, xKnockback, yKnockback));
	}
	public void buttonA_CrouchingLeft() {
		int activeFrames = 20;
		int hitStun = 40;
		int damage = 10;
		int xKnockback = 10;
		int yKnockback = -5;
		player.getHitBoxes().add(new HitBox((int)player.x - 20, (int)player.y + 22, 50, 10, activeFrames, hitStun,damage, xKnockback, yKnockback));
	}
	public void buttonA_AirRight() {
		// TODO Auto-generated method stub
	}
	public void buttonA_AirLeft() {
		// TODO Auto-generated method stub
	}

	@Override
	public void buttonA_Special() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buttonA_AirSpecial() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buttonB_StandingRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buttonB_StandingLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buttonB_CrouchingRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buttonB_CrouchingLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buttonB_AirRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buttonB_AirLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buttonB_Special() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buttonB_AirSpecial() {
		// TODO Auto-generated method stub
		
	}
}
