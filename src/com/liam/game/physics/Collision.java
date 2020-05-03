package com.liam.game.physics;

import java.awt.Point;

import com.liam.game.objects.Block;
import com.liam.game.objects.MovingBlock;

public class Collision {

	public static boolean playerBlock(Point p, Block b) {
		return b.contains(p);
	}
	
	public static boolean playerMovingBlock(Point p, MovingBlock b) {
		return b.contains(p);
	}
}
