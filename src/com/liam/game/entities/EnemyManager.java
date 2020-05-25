package com.liam.game.entities;

import java.util.ArrayList;

public class EnemyManager {
	private int enemyCount = 0;
	private ArrayList<Enemy> enemies;
	
	public EnemyManager() {
		enemies = new ArrayList<Enemy>();
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public void addEnemy(Enemy enemy) {
		enemyCount++;
		enemies.add(enemy);
	}

}
