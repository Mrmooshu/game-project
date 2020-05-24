package com.liam.game.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import com.liam.game.objects.Block;
import com.liam.game.objects.MovingBlock;

public abstract class Enemy {
	protected static int enemyCount;
	
	public Enemy() {
		enemyCount += 1;
	}
	
	public abstract void tick(Block[][] blocks, ArrayList<MovingBlock> movingBlocks);
	public abstract void draw(Graphics g);
}
