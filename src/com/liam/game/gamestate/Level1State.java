package com.liam.game.gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.liam.game.entities.Enemy;
import com.liam.game.entities.EnemyManager;
import com.liam.game.entities.Player;
import com.liam.game.mapping.Map;
import com.liam.game.objects.Block;
import com.liam.game.resources.Images;

import HUD.PlayerHUD;

public class Level1State extends GameState {
	
	private Player player;
	private Map map;
	private PlayerHUD playerHUD; 
	private EnemyManager enemies;
	private Enemy joe;
	
	public Level1State(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		player = new Player(30, 50);
		map = new Map("/Maps/map1.map");
		playerHUD = new PlayerHUD();
		enemies = new EnemyManager();
		joe = new Enemy(50, 50, 200, -20, 50);
		enemies.addEnemy(joe);
		
		xOffset = -200;
		yOffset = -400;
	}

	public void tick() {
		System.out.println((int)GameState.xOffset + " " + (int)GameState.yOffset);
		player.tick(map.getBlocks(), map.getMovingBlocks(), enemies.getEnemies());
		map.tick();
		playerHUD.tick(player.getHealth());
		for (int i = 0; i < enemies.getEnemies().size(); i++) {
			enemies.getEnemies().get(i).tick(map.getBlocks(), map.getMovingBlocks());
			if (enemies.getEnemies().get(i).getDie()) {
				enemies.getEnemies().remove(i);
			}
		}
	}

	public void draw(Graphics g) {
		g.drawImage(Images.backgrounds[0], -1000 - (int)GameState.xOffset, -380 - (int)GameState.yOffset, 7000, 800, null);
		player.draw(g);
		map.draw(g);
		playerHUD.draw(g);
		for (int i = 0; i < enemies.getEnemies().size(); i++) {
			enemies.getEnemies().get(i).draw(g);;
		}

	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ESCAPE) {
			gsm.states.push(new PauseState(gsm, xOffset, yOffset));//pause
		}
		player.keyPressed(k);
	}

	public void keyReleased(int k) {
		player.keyReleased(k);
	}
}
