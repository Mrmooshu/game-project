package com.liam.game.moveset;

import com.liam.game.entities.Player;

public abstract class MoveSet {

	public Player player;
	
	public MoveSet(Player player) {
		this.player = player;
	}
	
	public abstract void buttonA_StandingRight();
	public abstract void buttonA_StandingLeft();
	public abstract void buttonA_CrouchingRight();
	public abstract void buttonA_CrouchingLeft();
	public abstract void buttonA_AirRight();
	public abstract void buttonA_AirLeft();
	public abstract void buttonA_Special();
	public abstract void buttonA_AirSpecial();
	
	public abstract void buttonB_StandingRight();
	public abstract void buttonB_StandingLeft();
	public abstract void buttonB_CrouchingRight();
	public abstract void buttonB_CrouchingLeft();
	public abstract void buttonB_AirRight();
	public abstract void buttonB_AirLeft();
	public abstract void buttonB_Special();
	public abstract void buttonB_AirSpecial();
}
