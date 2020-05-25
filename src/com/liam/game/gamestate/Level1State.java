package com.liam.game.gamestate;

import java.awt.Graphics;

import com.liam.game.entities.Enemy;
import com.liam.game.entities.EnemyManager;
import com.liam.game.entities.Player;
import com.liam.game.mapping.Map;
import com.liam.game.objects.Block;

import HUD.PlayerHUD;

public class Level1State extends GameState {

	private Player player;
	private Map map;
	private PlayerHUD playerHUD; 
	private EnemyManager enemies;
	private Enemy joe;
	private Enemy joe2;
	
	public Level1State(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		player = new Player(30, 50);
		map = new Map("/Maps/map1.map");
		playerHUD = new PlayerHUD();
		enemies = new EnemyManager();
		joe = new Enemy(30, 30, 200, 40);
		joe2 = new Enemy(30, 30, 300, 40);
		enemies.addEnemy(joe);
		enemies.addEnemy(joe2);
		
		xOffset = -200;
		yOffset = -400;
	}

	public void tick() {
		player.tick(map.getBlocks(), map.getMovingBlocks(), enemies.getEnemies());
		map.tick();
		playerHUD.tick(player.getHealth());
		joe.tick(map.getBlocks(), map.getMovingBlocks());
		joe2.tick(map.getBlocks(), map.getMovingBlocks());
	}

	public void draw(Graphics g) {
		player.draw(g);
		map.draw(g);
		playerHUD.draw(g);
		joe.draw(g);
		joe2.draw(g);

	}

	public void keyPressed(int k) {
		player.keyPressed(k);
	}

	public void keyReleased(int k) {
		player.keyReleased(k);
	}
}
