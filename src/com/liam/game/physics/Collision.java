package com.liam.game.physics;

import java.awt.Point;

import com.liam.game.objects.Block;

public class Collision {

	public static boolean playerBlock(Point p, Block b) {
		return b.contains(p);
	}
}
