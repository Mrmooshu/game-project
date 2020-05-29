package com.liam.game.gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.liam.game.entities.Editor;
import com.liam.game.entities.Enemy;
import com.liam.game.entities.EnemyManager;
import com.liam.game.entities.Inventory;
import com.liam.game.entities.Item;
import com.liam.game.entities.Player;
import com.liam.game.main.GamePanel;
import com.liam.game.mapping.Map;
import com.liam.game.resources.Images;

import HUD.PlayerHUD;

public class MapEditor extends GameState{

	
	private Editor editor;
	private Map map;
	
	public MapEditor(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		map = new Map("/Maps/map1.map");
		editor = new Editor(map);
		GameState.xOffset = 0 - GamePanel.WIDTH / 2;
		GameState.yOffset = 0 - GamePanel.HEIGHT / 2;
	}

	public void tick() {
		editor.tick();
		map.loadMap(editor.getMap());
	}

	public void draw(Graphics g) {
		g.drawImage(Images.backgrounds[0], -1000 - (int)GameState.xOffset, -380 - (int)GameState.yOffset, 7000, 800, null);
		map.draw(g);
		editor.draw(g);
	}

	public void keyPressed(int k) {
		editor.keyPressed(k);
	}

	public void keyReleased(int k) {
	}
}
