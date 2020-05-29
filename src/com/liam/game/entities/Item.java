package com.liam.game.entities;

import java.awt.image.BufferedImage;

public class Item {

	private BufferedImage icon;
	
	public Item(BufferedImage icon) {
		this.icon = icon;
	}
	
	public BufferedImage getIcon() {
		return icon;
	}
}
