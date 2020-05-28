package com.liam.game.entities;

import java.util.LinkedList;

public class Inventory {

	private LinkedList<Item> items;
	private Item weapon1;
	private Item weapon2;
	private Item style;
	
	public Inventory() {
		items = new LinkedList<Item>();
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	public void removeItem(Item item) {
		items.remove(item);
	}
	public void equipWeapon1(Item item) {
		weapon1 = item;
	}
	public void equipWeapon2(Item item) {
		weapon2 = item;
	}
	public void equipStyle(Item item) {
		style = item;
	}
}
