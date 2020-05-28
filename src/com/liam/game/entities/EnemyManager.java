package com.liam.game.entities;

import java.util.LinkedList;

public class EnemyManager {
	private int enemyCount = 0;
	private LinkedList<Enemy> enemies;
	
	public EnemyManager() {
		enemies = new LinkedList<Enemy>();
	}
	
	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}
	
	public void addEnemy(Enemy enemy) {
		enemyCount++;
		enemies.add(enemy);
	}
	
	public void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
		enemyCount--;
	}

}
