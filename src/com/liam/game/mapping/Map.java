package com.liam.game.mapping;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import com.liam.game.objects.Block;
import com.liam.game.objects.MovingBlock;

public class Map {

	private String path;
	private String line;
	private int blockCount;
	
	private Block[] blocks;
	private ArrayList<MovingBlock> movingBlocks;
	
	public Map(String loadPath) {
		path = loadPath;
		loadMap();
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < blocks.length; i++) {
			blocks[i].draw(g);
		}
		
		for (int i = 0; i < movingBlocks.size(); i++) {
			movingBlocks.get(i).draw(g);
		}
	}
	
	public void tick() {
		for (int i = 0; i < movingBlocks.size(); i++) {
			movingBlocks.get(i).tick();
		}
	}
	
	
	public void loadMap() {
		InputStream is = this.getClass().getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			blockCount = Integer.parseInt(br.readLine());//reads in width of block area
			blocks = new Block[blockCount];//stores block data
			line = br.readLine();//skips a line

			for (int i = 0; i < blockCount; i++) {
				line = br.readLine();
				String[] tokens = line.split(",");
				blocks[i] = new Block(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
						Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
			}
			line = br.readLine();//skips a line
			int length = Integer.parseInt(br.readLine());//stores the number of moving blocks
			movingBlocks = new ArrayList<MovingBlock>();
			
			for (int i = 0; i < length; i++) {
				line = br.readLine();//reads moving block data
				String[] tokens = line.split(",");//splits moving block data
				movingBlocks.add(new MovingBlock(Integer.parseInt(tokens[0]),
						Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]),
						Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]),
						Integer.parseInt(tokens[5])));
			}
		}
		catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public String mapToString() {
		InputStream is = this.getClass().getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			String hold = "";
			String line = br.readLine();
			while (line != null) {
				hold = hold + line + System.lineSeparator();
				line = br.readLine();
			}
			return hold;
		}
		catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public Block[] getBlocks() {
			return blocks;
	}
	
	public ArrayList<MovingBlock> getMovingBlocks(){
		return movingBlocks;
	}
}
