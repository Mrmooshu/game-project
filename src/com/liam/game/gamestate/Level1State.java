package com.liam.game.gamestate;

import java.awt.Graphics;

import com.liam.game.entities.Player;
import com.liam.game.mapping.Map;
import com.liam.game.objects.Block;

public class Level1State extends GameState {

	private Player player;
	private Map map;
	
	public Level1State(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		player = new Player(30, 50);
		map = new Map("/Maps/map1.map");
		
		xOffset = -200;
		yOffset = -400;
	}

	public void tick() {
		player.tick(map.getBlocks(), map.getMovingBlocks());
		map.tick();
	}

	public void draw(Graphics g) {
		player.draw(g);
		map.draw(g);
	}

	public void keyPressed(int k) {
		player.keyPressed(k);
	}

	public void keyReleased(int k) {
		player.keyReleased(k);
	}
}
