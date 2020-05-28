package com.liam.game.entities;

import java.util.LinkedList;

public class HurtBoxManager {
	private int hurtBoxCount = 0;
	private LinkedList<HurtBox> hurtBoxes;
	
	public HurtBoxManager() {
		hurtBoxes = new LinkedList<HurtBox>();
	}
	
	public LinkedList<HurtBox> getHurtBoxes() {
		return hurtBoxes;
	}
	
	public void addHurtBox(HurtBox hurtBox) {
		hurtBoxCount++;
		hurtBoxes.add(hurtBox);
	}
	
	public void removeHurtBox(HurtBox hurtBox) {
		hurtBoxes.remove(hurtBox);
		hurtBoxCount--;
	}

}
