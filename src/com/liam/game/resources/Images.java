package com.liam.game.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {

	public static BufferedImage[] blocks;
	public static BufferedImage[] playerSprites;
	public static BufferedImage[] backgrounds;
	
	public Images() {
		blocks = new BufferedImage[2];
		playerSprites = new BufferedImage[6];
		backgrounds = new BufferedImage[1];

		try {
			blocks[0] = ImageIO.read(getClass().getResourceAsStream("/Blocks/block_brick.png"));
			blocks[1] = ImageIO.read(getClass().getResourceAsStream("/Blocks/george.png"));
			playerSprites[0] = ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/character right.png"));
			playerSprites[1] = ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/character left.png"));
			playerSprites[2] = ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/character moving right.png"));
			playerSprites[3] = ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/character moving left.png"));
			playerSprites[4] = ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/character fall right.png"));
			playerSprites[5] = ImageIO.read(getClass().getResourceAsStream("/PlayerSprites/character fall left.png"));
			backgrounds[0] = ImageIO.read(getClass().getResourceAsStream("/Blocks/background.png"));

			} catch (IOException e) {
				e.printStackTrace();
				}
	}
}
