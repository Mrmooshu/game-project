package com.liam.game.resources;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
	
	private ArrayList<BufferedImage> frameList;
	private int frameCounter;
	private int frameHold;
	private int holdCounter = 0;
	private boolean holdLastFrame = false;
	private int holdFor = 0;
	
	public Animation(ArrayList<BufferedImage> frameList, int frameHold) {
		this.frameList= frameList;
		this.frameHold = frameHold;
		frameCounter = 0;
	}
	
	public void drawNextFrame(Graphics g, int x, int y, int width, int height) {
		g.drawImage(frameList.get(frameCounter), x, y, width, height, null);
		if (holdCounter == frameHold) {
			holdCounter = 0;
			frameCounter++;
		}
		else { 
			holdCounter++;
		}
		if (frameCounter == frameList.size() && !holdLastFrame) {
			frameCounter = 0;
		}
		if (frameCounter == frameList.size() && holdLastFrame) {
			frameCounter -= holdFor;
		}
	}
	
	public void setFrameCounter(int number) {
		frameCounter = number;
	}
	public void holdLastFrame(int howMany) {
		holdLastFrame = true;
		holdFor = howMany;
	}
	public void dontHoldLastFrame() {
		holdLastFrame = false;
		holdFor = 0;
	}

}
