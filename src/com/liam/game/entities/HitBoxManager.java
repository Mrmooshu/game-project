package com.liam.game.entities;

import java.util.LinkedList;

public class HitBoxManager {
	private int hitBoxCount = 0;
	private LinkedList<HitBox> hitBoxes;
	
	public HitBoxManager() {
		hitBoxes = new LinkedList<HitBox>();
	}
	
	public LinkedList<HitBox> getHitBoxes() {
		return hitBoxes;
	}
	
	public void addHitBox(HitBox hitBox) {
		hitBoxCount++;
		hitBoxes.add(hitBox);
	}
	
	public void removeHitBox(HitBox hitBox) {
		hitBoxes.remove(hitBox);
		hitBoxCount--;
	}

}
