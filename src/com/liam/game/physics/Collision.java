package com.liam.game.physics;

import java.awt.Point;
import java.awt.Rectangle;

import com.liam.game.entities.HitBox;
import com.liam.game.entities.HurtBox;
import com.liam.game.objects.Block;
import com.liam.game.objects.MovingBlock;

public class Collision {

	public static boolean playerBlock(Point p, Block b) {
		return b.contains(p);
	}
	
	public static boolean playerMovingBlock(Point p, MovingBlock b) {
		return b.contains(p);
	}
	
	public static boolean enemyBlock(Point p, Block b) {
		return b.contains(p); 
	}
	
//	public static boolean playerEnemy(Rectangle a, Rectangle b) {
//		return a.intersects(b);
//	}
	
	public static boolean hitBoxHurtBox(HitBox a, HurtBox b) {
		return a.intersects(b);
	}

}
