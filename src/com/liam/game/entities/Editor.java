package com.liam.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import com.liam.game.gamestate.GameState;
import com.liam.game.main.GamePanel;
import com.liam.game.mapping.Map;

public class Editor {

	private double x, y;
	private int cordX = 0, cordY = 0;
	private String map;

	public Editor(Map map) {
		this.map = map.mapToString();
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
	}
	
	public void tick() {
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 8, 8);
	}
	
	public void changeTile(int x, int y) throws IOException {
		String newF = "";
		StringReader sr = new StringReader(map);
		BufferedReader br = new BufferedReader(sr);
		String line = br.readLine();
		for (int i = 0; i  < cordY + 2; i++) {
			newF = newF + line + System.lineSeparator();
			line = br.readLine();
		}
		String[] tokens = line.split(" ");
		if (Integer.parseInt(tokens[cordX]) == 0) {
			tokens[cordX] = "1";
		}
		else if (Integer.parseInt(tokens[cordX]) == 1) {
			tokens[cordX] = "0";
		}
		line = "";
		for (int i = 0; i < tokens.length; i++) {
			line += tokens[i] + " ";	
		}
		line = line.substring(0, line.length() - 1);
		while (line != null) {
			newF = newF + line + System.lineSeparator();
			line = br.readLine();
		}
		br.close();
		map = newF;
		FileWriter fw = new FileWriter("./res/Maps/map1.map");
		fw.write(newF);
		fw.close();
	}
	
	public String getMap() {
		return map;
	}
	
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_D) {
			GameState.xOffset += 8;
			cordX += 1;
		}
		if (k == KeyEvent.VK_A) {
			GameState.xOffset -= 8;
			cordX -= 1;
		}
		if (k == KeyEvent.VK_W) {
			GameState.yOffset -= 8;
			cordY -= 1;
		}
		if (k == KeyEvent.VK_S) {
			GameState.yOffset += 8;
			cordY += 1;
		}
		if (k == KeyEvent.VK_SPACE) {
			try {
				changeTile(cordX, cordY);
			}
			catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
		}

	}
}
